package com.example.questApp.repos;

import com.example.questApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    //findByile başlayan işlemleri tanımlıyorum, objenin içerisindeki herhangi bir alan için, jpa gerisini hallediyor.
    List<Post> findByUserId(Long userId);

}
