package com.finalproject.internet.banking.internetbanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.dtos.EmailDTO;
import com.finalproject.internet.banking.internetbanking.services.EmailSenderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/emails")
public class EmailController {
    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<Void> sendEmail(@RequestBody EmailDTO emailDTO) {
        emailSenderService.sendEmail(emailDTO);
        return ResponseEntity.ok().build();
    }
}
