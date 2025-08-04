// Em um novo arquivo: LogoutController.java
package com.finalproject.internet.banking.internetbanking.controllers;

import com.finalproject.internet.banking.internetbanking.services.TokenDenyListService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private TokenDenyListService tokenDenyListService;

    @PostMapping
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            tokenDenyListService.addToDenyList(token);
        }
        return ResponseEntity.ok().build();
    }
}