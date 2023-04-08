package com.example.task.service;


import com.example.task.entity.User;

public interface UserService {
    User getById(long id);

    boolean save(User user);

}
