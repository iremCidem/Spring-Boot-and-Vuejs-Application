package com.example.questApp.controller;

import com.example.questApp.dto.Post.PostCreateDto;
import com.example.questApp.dto.Post.PostUpdateDto;
import com.example.questApp.entities.Post;
import com.example.questApp.services.PostService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return postService.getSelectedPost(postId);
    }

    @PostMapping
    public List<Post> createPost(@RequestBody PostCreateDto post){
        return (List<Post>) postService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateDto newPost){
        return postService.updatePost(postId, newPost);
    }

    @DeleteMapping("/{postId}")
     public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }


}
