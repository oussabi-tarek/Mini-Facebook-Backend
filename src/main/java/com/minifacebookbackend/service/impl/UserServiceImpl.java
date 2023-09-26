package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.repository.UserRepository;
import com.minifacebookbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        if(user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user to save is null");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String userId, User user) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user id is not valid"));

        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setFirstName(user.getFirstName());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user id is not valid"));
        userRepository.delete(user);
    }
}
