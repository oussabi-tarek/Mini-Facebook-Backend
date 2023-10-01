package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.service.ApiConsume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(ResourcePath.AUTHENTIFICATION)
public class AuthRessource {
    @Autowired
    private ApiConsume apiConsume;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Object>> login(String email, String password){
        return apiConsume.authenticate(email, password);
    }
}
