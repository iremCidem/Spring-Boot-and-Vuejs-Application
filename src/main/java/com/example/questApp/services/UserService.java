package com.example.questApp.services;

import com.example.questApp.entities.User;
import com.example.questApp.repos.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
     UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //findAll, veri tabanındaki tüm kayıtları getirir.
    public ResponseEntity<Object> getAllUsers(){
        List<User> userList = userRepository.findAll();  // findAll() doğrudan List<User> döndürür
        if (userList.isEmpty()) {  // Liste boşsa
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userList);
        }
    }

    //save metodu, parametre olarak aldığı user nesnesini kaydeder ve veritabanına eklenmiş (ID atanmış) haliyle geri döner.
    public User createUser(User user){
        return (User) userRepository.save(user);
    }


    //orElse ile eğer dbden bdönecek bir veri bulamazsa null atmasını söyledik.
    public User getUserById(Long userId) {
        return (User) userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User newUser) {
        Optional oldUser = userRepository.findById(userId);
        if (oldUser.isPresent()) {
            User userToUpdate = (User) oldUser.get();
            userToUpdate.setPass_word(newUser.getPass_word());
            userToUpdate.setUser_name(newUser.getUser_name());
            userRepository.save(userToUpdate);
            return userToUpdate;
        } else {
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
