package com.example.questApp.controller;

import com.example.questApp.entities.Post;
import com.example.questApp.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    //request param yanındaki değerleri sırasıyla url parametreleri olarak çekiyor. ?userId=1&...
    public List<Post> getPostList(@RequestParam Optional<Long> userId){
       return postService.getPosts(userId);
    }

}
