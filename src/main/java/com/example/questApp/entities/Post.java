package com.example.questApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

//veri tabanındaki post tablosuna karşılık gelir.
@Entity
//bununla getter, setter fonksiyonlarını getiriyoruz.
@Data
public class Post {

    //primary key
    @Id
    Long id;

    //bu alan post user ilişkisini temsil eder.
    //birden fazla post tek bir usera bağlı olduğu için many to one.
    @ManyToOne(fetch = FetchType.LAZY)
    //veritabanında user_id foreign key olur.
    @JoinColumn(name = "user_id", nullable = false)
    //user silinirse ona bağlı postlarda silinir.
    @OnDelete(action = OnDeleteAction.CASCADE)
    //JSON cevabında user bilgisi gizlenir (sonsuz döngüyü önler)
    @JsonIgnore
    private User user;

    String title;

    String text;
}

//ctrl + alt + l , format code