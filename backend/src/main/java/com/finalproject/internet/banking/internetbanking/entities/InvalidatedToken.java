package com.finalproject.internet.banking.internetbanking.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "invalidated_token")
@Getter
@Setter
@NoArgsConstructor
public class InvalidatedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 512)
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;
    public InvalidatedToken(String token, Instant expiryDate) {
        this.token      = token;
        this.expiryDate = expiryDate;
    }
}
