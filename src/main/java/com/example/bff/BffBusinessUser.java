package com.example.bff;

import com.example.bff.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@Service
public class BffBusinessUser {
    @Value("http://localhost:8081")
    private String usersServiceUrl;

    private static final Logger log = Logger.getLogger(BffBusinessUser.class.toString());

    public void UsersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getUsers() {
        return restTemplate.getForEntity(usersServiceUrl + "/users", String.class);
    }

    public ResponseEntity<String> getUserById(String id) {
        return restTemplate.getForEntity(usersServiceUrl + "/users/" + id, String.class);
    }
}

