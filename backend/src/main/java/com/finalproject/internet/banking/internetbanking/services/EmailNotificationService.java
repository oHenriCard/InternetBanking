package com.finalproject.internet.banking.internetbanking.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import com.finalproject.internet.banking.internetbanking.entities.User;

@Service
public class EmailNotificationService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${email.service.url}")
    private String emailServiceURL;

    public void sendWelcomeEmail(User user) {
        Map<String, String> payload = new HashMap<>();
        payload.put("to",  user.getEmail());
        payload.put("subject", "Bem-Vindo ao Internet Banking!");
        payload.put("body", "Olá" + user.getName() + 
                    "\nSua conta foi criada com sucesso." +
                    "\nAgência: " + user.getAccount().getBranch() +
                    "\nConta: " + user.getAccount().getAccountNum());
        restTemplate.postForEntity(emailServiceURL, payload, String.class);
    }
    
    public void sendTransactionEmail(User user, String subject, BigDecimal value, BigDecimal newBalance) {
        Map<String, String> payload = new HashMap<>();
        String body = String.format("Você recebeu um %s de R$%.2f. Saldo atual: R$%.2f",
                                    subject.toLowerCase(), value, newBalance);
        payload.put("to", user.getEmail());
        payload.put("subject", subject + " realizado");
        payload.put("body", body);

        restTemplate.postForEntity(emailServiceURL, payload, String.class);
    }

}
