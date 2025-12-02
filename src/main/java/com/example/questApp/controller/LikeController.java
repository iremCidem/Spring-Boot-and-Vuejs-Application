package com.example.questApp.controller;

import com.example.questApp.dto.Like.LikeCreateDto;
import com.example.questApp.entities.Like;
import com.example.questApp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")
public class LikeController {

    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent()) {
            return likeService.getLikesByUserIdAndPostId(userId.get(), postId.get());
        } else if (postId.isPresent()) {
            return likeService.getLikesByPostId(postId.get());
        } else if (userId.isPresent()) {
            return likeService.getLikesByUserId(userId.get());
        } else {
            return likeService.getAllLikes();
        }
    }

    @GetMapping("/{likeId}")
    public Optional<Like> getLike(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like addLike(@RequestBody LikeCreateDto like) {
        return likeService.addLike(like);
    }

    @DeleteMapping("/{likeId}")
    public void removeLike(@PathVariable Long likeId) {
       likeService.removeLike(likeId);
    }
}
