package com.example.questApp.services;

import com.example.questApp.dto.Like.LikeCreateDto;
import com.example.questApp.entities.Like;
import com.example.questApp.entities.Post;
import com.example.questApp.entities.User;
import com.example.questApp.repos.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    public LikeRepository likeRepository;
    public UserService userService;
    public PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Like> getLikesByUserIdAndPostId(Long userId, Long postId) {
        return likeRepository.getLikesByUserIdAndPostId(userId, postId);
    }

    public List<Like> getLikesByPostId(Long postId) {
        return likeRepository.getLikesByPostId(postId);
    }

    public List<Like> getLikesByUserId(Long userId) {
        return likeRepository.getLikesByUserId(userId);
    }

    public List<Like> getAllLikes() {
        return  likeRepository.findAll();
    }

    public Optional<Like> getLikeById(Long likeId) {
        return likeRepository.findById(likeId);
    }

    public Like addLike(LikeCreateDto like) {
        User user = userService.getUserById(like.getUserId());
        Post post = postService.getSelectedPost(like.getPostId());
        if (user != null && post != null) {
            Like likeEntity = new Like();
            likeEntity.setUser(user);
            likeEntity.setPost(post);
            likeEntity.setId(like.getId());
            return likeRepository.save(likeEntity);
        }
        else return null;
    }


    public void removeLike(Long likeId) {
        Like like = likeRepository.findById(likeId).get();
        if(like != null){
            likeRepository.delete(like);
        }

    }
}
