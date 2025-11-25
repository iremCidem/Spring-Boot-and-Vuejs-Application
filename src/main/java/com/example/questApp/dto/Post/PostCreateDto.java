package com.example.questApp.dto.Post;

import lombok.Data;

@Data

//bir post create etme objesi yaratıyoruz.
public class PostCreateDto {
Long id;
String title;
String text;
Long userId;
}
