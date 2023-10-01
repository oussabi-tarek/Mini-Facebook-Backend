package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.User;
import com.minifacebookbackend.repository.UserRepository;
import com.minifacebookbackend.service.ApiConsume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.HashMap;

@Service
public class ApiConsumeImpl implements ApiConsume {
    @Value("${GRANT_TYPE}")
    private String grantType;
    @Value("${CLIENT_ID}")
    private String clientId;
    @Value("${CLIENT_SECRET}")
    private String clientSecret;
    @Value("${KEYCLOAK_SERVER_URL}")
    private String keycloakServerUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<HashMap<String, Object>> sendLoginRequestToKeycloak(String email, String password) {
        System.out.println("grant type : "+grantType);
        System.out.println("Client id : "+clientId);
        System.out.println("Client secret : "+clientSecret);

        HttpHeaders headers = new HttpHeaders();
        String loginApi = keycloakServerUrl+"/protocol/openid-connect/token";
        System.out.println("Client id : "+loginApi);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("client_id", clientId);
        requestBody.add("client_secret", clientSecret);
        requestBody.add("username", email);
        requestBody.add("password", password);
        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<HashMap<String,Object>> response = restTemplate.exchange(loginApi, HttpMethod.POST, httpEntity,new ParameterizedTypeReference<HashMap<String, Object>>(){});
        if(response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        else {
            HashMap<String, Object> body = new HashMap<>();
            body.put("Error","Vos coordonnées sont incorrectes! Veuillez réassayer");
            return ResponseEntity.status(response.getStatusCode()).body(body);
        }
    }

    @Override
    public ResponseEntity<HashMap<String, Object>> authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        //ResponseEntity<HashMap<String, Object>> responseEntity;
        if(user != null){
            System.out.println(" Passsword  : "+password);
            ResponseEntity<HashMap<String, Object>> responseEntity = sendLoginRequestToKeycloak(email, password);
            System.out.println(" Passsword  : "+password);
            System.out.println(" HTTP STATUS : "+responseEntity.getStatusCode());
            return responseEntity;
        }
        else {
            HashMap<String, Object> body = new HashMap<>();
            body.put("Error","Vos coordonnées sont incorrectes! Veuillez réassayer");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }
    }
}
