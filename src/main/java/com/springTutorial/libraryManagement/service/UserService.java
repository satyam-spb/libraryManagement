package com.springTutorial.libraryManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springTutorial.libraryManagement.entity.User;
import com.springTutorial.libraryManagement.exception.UserNotFoundException;
import com.springTutorial.libraryManagement.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id %d not found".formatted(id)));
    }

    public User updateUser(Long id, User updatedUser){
        User existingUser = getUserById(id);
        existingUser.setUserName(updatedUser.getUserName());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
