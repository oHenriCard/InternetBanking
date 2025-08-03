package com.finalproject.emailservice.email_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.emailservice.email_service.dto.EmailDTO;
import com.finalproject.emailservice.email_service.services.EmailService;


@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO);
        // Retorna uma resposta HTTP 200 (OK) com uma mensagem de sucesso
        return ResponseEntity.status(HttpStatus.OK).body("Solicitação de envio de e-mail recebida.");
    }
}
