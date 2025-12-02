package com.example.questApp.dto.Like;

import lombok.Data;

@Data
public class LikeCreateDto {
    Long id;
    Long userId;
    Long postId;
}
