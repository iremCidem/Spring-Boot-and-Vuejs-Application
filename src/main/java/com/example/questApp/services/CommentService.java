package com.example.questApp.services;

import com.example.questApp.dto.Comment.CommentCreateDto;
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
        if (userId.isPresent()) {
            return commentRepository.findByUserId(userId);
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId);
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
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }
}
