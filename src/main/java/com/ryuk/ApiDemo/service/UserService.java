package com.ryuk.ApiDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuk.ApiDemo.model.User;
import com.ryuk.ApiDemo.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
    	userRepository.save(user);
    }
    
    public List<User> getAllUsers(){
    	return userRepository.getAlluser();
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}