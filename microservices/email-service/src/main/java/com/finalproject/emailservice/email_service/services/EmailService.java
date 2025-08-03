package com.finalproject.emailservice.email_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.finalproject.emailservice.email_service.dto.EmailDTO;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmail(EmailDTO emailDTO) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender); // E-mail remetente
            message.setTo(emailDTO.getTo()); // E-mail destinatário [cite: 42]
            message.setSubject(emailDTO.getSubject()); // Assunto do e-mail [cite: 43]
            message.setText(emailDTO.getBody()); // Corpo do e-mail [cite: 44]

            mailSender.send(message);
            System.out.println("E-mail enviado com sucesso para: " + emailDTO.getTo());
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }
}