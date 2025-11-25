package com.example.questApp.services;

import com.example.questApp.dto.Post.PostCreateDto;
import com.example.questApp.dto.Post.PostUpdateDto;
import com.example.questApp.entities.Post;
import com.example.questApp.entities.User;
import com.example.questApp.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final UserService userService;
    private PostRepository postRepository;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getPosts(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return postRepository.findAll();
    }

    public Post getSelectedPost(Long postId){
        return postRepository.findById(postId).orElse(null);
    }

    public List<Post> createPost(PostCreateDto postDto){
       User user = userService.getUserById((postDto.getUserId()));
       if(user==null){
           return null;
       }
       Post newPost = new Post();
       newPost.setTitle(postDto.getTitle());
       newPost.setText(postDto.getText());
       newPost.setUser(user);
       newPost.setId(postDto.getId());
       postRepository.save(newPost);
       return postRepository.findAll();
    }

    public Post updatePost(Long postId, PostUpdateDto newPost){
        Post post = postRepository.findById(postId).orElse(null);
        if(post!=null){
            post.setTitle(newPost.getTitle());
            post.setText(newPost.getText());
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

}
