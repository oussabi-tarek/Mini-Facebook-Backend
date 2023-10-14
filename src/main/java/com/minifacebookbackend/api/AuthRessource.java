package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.service.ApiConsume;
import com.minifacebookbackend.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping(ResourcePath.AUTHENTIFICATION)

public class AuthRessource {
    @Autowired
    private ApiConsume apiConsume;
    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(String email, String password){
        return apiConsume.authenticate(email, password);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("image") MultipartFile file) throws IOException {
        UserCommand userCommand = new UserCommand(firstName, lastName, email, password, null , null);
        return registrationService.register(userCommand, file);
    }
    @PostMapping("/validateToken")
    public ResponseEntity<HashMap<String,Object>> validateToken(String token){
        return apiConsume.validateToken(token);
    }
}
