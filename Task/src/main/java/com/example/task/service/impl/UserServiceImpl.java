package com.example.task.service.impl;

import com.example.task.entity.User;
import com.example.task.repository.UserRepository;
import com.example.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean save(User user) {
        User userByEmail = userRepository.findByEmailIgnoreCase(user.getEmail());
        User userByUserName = userRepository.findByUsername(user.getUsername());
        if (userByUserName != null | user.getUsername().contains(" ")
                | userByEmail != null) {
            return false;
        }
        userRepository.save(user);
        return true;
    }
}
