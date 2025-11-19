package com.example.questApp.repos;

import com.example.questApp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
