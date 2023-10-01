package com.minifacebookbackend.service;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface ApiConsume {
    ResponseEntity<HashMap<String, Object>> sendLoginRequestToKeycloak(String username, String password);
    ResponseEntity<HashMap<String, Object>> authenticate(String email, String password);
}