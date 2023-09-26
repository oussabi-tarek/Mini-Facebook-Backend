package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.model.User;

public interface UserService {
    User saveUser(User user);
    User updateUser(String userId, User user);
    void deleteUser(String userId);
}
