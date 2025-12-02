package com.example.questApp.repos;

import com.example.questApp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> getLikesByUserId(Long userId);

    List<Like> getLikesByPostId(Long postId);

    List<Like> getLikesByUserIdAndPostId(Long userId, Long postId);
}

// ctrl+alt+o unused imports sil.