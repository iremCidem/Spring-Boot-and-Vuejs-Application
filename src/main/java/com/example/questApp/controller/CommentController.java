package com.example.questApp.controller;

import com.example.questApp.dto.Comment.CommentCreateDto;
import com.example.questApp.dto.Comment.CommentUpdateDto;
import com.example.questApp.entities.Comment;
import com.example.questApp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getComments(
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Long> postId) {

        return commentService.getComments(userId, postId);
    }


    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment addComment(@RequestBody CommentCreateDto comment) {
        return commentService.addComment(comment);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto comment) {
        return commentService.updateComment(commentId, comment);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }


}
