package com.finalproject.internet.banking.internetbanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.entities.User;
import com.finalproject.internet.banking.internetbanking.services.JWTokenService;

record AuthenticationData(String email, String password) {}
record JWTTokenData(String token) {}

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> makeLogin (@RequestBody AuthenticationData data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(tokenJWT));
    }
}
