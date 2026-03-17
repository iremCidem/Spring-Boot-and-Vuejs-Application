package com.example.questApp.dto.Post;

import lombok.Data;

@Data

//bir post create etme objesi yaratıyoruz.
public class PostCreateDto {
String title;
String text;
Long userId;
}
