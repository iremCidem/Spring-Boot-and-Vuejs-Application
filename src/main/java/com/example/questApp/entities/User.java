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

}
