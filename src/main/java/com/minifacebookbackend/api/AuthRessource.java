package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.UserCommand;
import com.minifacebookbackend.service.ApiConsume;
import com.minifacebookbackend.service.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(ResourcePath.AUTHENTIFICATION)
public class AuthRessource {
    @Autowired
    private ApiConsume apiConsume;
    @Autowired
    private RegistrationServiceImpl registrationService;

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Object>> login(String email, String password){
        return apiConsume.authenticate(email, password);
    }
    @PostMapping("/register")
    public ResponseEntity<?>  register(@RequestBody UserCommand userCommand){
        return registrationService.register(userCommand);
    }
}
