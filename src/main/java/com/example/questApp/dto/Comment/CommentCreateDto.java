package com.example.questApp.dto.Comment;

import lombok.Data;

@Data
public class CommentCreateDto {
    Long id;
    String text;
    Long userId;
    Long postId;
}
