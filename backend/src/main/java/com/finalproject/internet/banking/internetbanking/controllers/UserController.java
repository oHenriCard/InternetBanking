package com.finalproject.internet.banking.internetbanking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.dtos.UserDTO;
import com.finalproject.internet.banking.internetbanking.entities.User;
import com.finalproject.internet.banking.internetbanking.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Cadastra um novo usuário e cria uma conta corrente automaticamente")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
        User newUser = userService.register(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok(userDTOs);
    }

}
