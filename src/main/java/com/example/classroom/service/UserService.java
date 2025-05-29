package com.example.classroom.service;

import com.example.classroom.entity.User;

public interface UserService {
    User register(String phoneOrEmail, String password);
    User login(String userName, String password);
    User getUserById(Integer id);
}
