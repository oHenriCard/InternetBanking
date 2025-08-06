package com.finalproject.internet.banking.internetbanking.repositories;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.internet.banking.internetbanking.entities.InvalidatedToken;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, Long>{
    boolean existsByToken(String token);
    void deleteByExpiryDateBefore(Instant now);    
}
