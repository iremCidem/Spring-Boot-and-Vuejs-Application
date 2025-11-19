package com.example.questApp.controller;

import com.example.questApp.entities.User;
import com.example.questApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<Object> getUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public User createUser(@RequestBody User user) {
       return userService.createUser(user);
    }

    //PathVariable, url üzerinden veri alır.
    @GetMapping("/{userId}")
    public User getSelectedUser(@PathVariable Long userId) {
       return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser) {
       return userService.updateUser(userId, newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

}
