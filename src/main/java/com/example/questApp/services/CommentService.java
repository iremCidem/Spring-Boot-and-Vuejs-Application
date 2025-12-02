package com.example.questApp.services;

import com.example.questApp.dto.Comment.CommentCreateDto;
import com.example.questApp.dto.Comment.CommentUpdateDto;
import com.example.questApp.entities.Comment;
import com.example.questApp.entities.Post;
import com.example.questApp.entities.User;
import com.example.questApp.repos.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    public CommentRepository commentRepository;
    UserService userService;
    PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getComments(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else {
            return commentRepository.findAll();
        }
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment addComment(CommentCreateDto commentCreateDto) {
        User selectedUser = userService.getUserById(commentCreateDto.getUserId());
        Post selectedPost = postService.getSelectedPost(commentCreateDto.getPostId());
        if (selectedUser != null && selectedPost != null) {
            Comment comment = new Comment();
            comment.setUser(selectedUser);
            comment.setPost(selectedPost);
            comment.setText(commentCreateDto.getText());
            comment.setId(commentCreateDto.getId());
            // burada zaten save kayıt ettiği objeyi dönüyor.
            return commentRepository.save(comment);
        } else
            return null;

    }

    public Comment updateComment(Long commentId, CommentUpdateDto comment) {
        Optional<Comment> oldComment = commentRepository.findById(commentId);
        if (oldComment.isPresent()) {
            Comment existingComment = oldComment.get();
            // setText void döner.
            existingComment.setText(comment.getText());
            return commentRepository.save(existingComment);
        } else {
            return null;
        }
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> oldComment = commentRepository.findById(commentId);
        oldComment.ifPresent(comment -> commentRepository.delete(comment));
    }
}
