package com.example.questApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user") // bu table adını özelleştirmek için.
@Data
public class User {
    @Id
    Long id;
    String user_name;
    String pass_word;

    public String getUser_name() {
        return user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
