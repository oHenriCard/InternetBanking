package com.finalproject.internet.banking.internetbanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.finalproject.internet.banking.internetbanking.entities.InvalidatedToken;
import com.finalproject.internet.banking.internetbanking.repositories.InvalidatedTokenRepository;

import jakarta.transaction.Transactional;

import java.time.Instant;

@Service
public class TokenDenyListService {
    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    private JWTokenService jwTokenService;

    @Transactional
    public void addToDenyList(String token) {
        Instant expiryDate = jwTokenService.getExpirationDate(token); 
        InvalidatedToken invalidatedToken = new InvalidatedToken(token, expiryDate);
        invalidatedTokenRepository.save(invalidatedToken);
    }

    public boolean isTokenDenied(String token) {
        return invalidatedTokenRepository.existsByToken(token);
    }

    @Transactional
    @Scheduled(cron = "0 0 * * * *") 
    public void cleanUpExpiredTokens() {
        System.out.println("Limpando tokens expirados da deny list...");
        invalidatedTokenRepository.deleteByExpiryDateBefore(Instant.now());
        System.out.println("Limpeza concluída.");
    }
}