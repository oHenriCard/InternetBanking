package com.finalproject.internet.banking.internetbanking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.finalproject.internet.banking.internetbanking.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTokenService {

    // Lê a senha secreta do application.properties [cite: 50, 51, 52]
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            // Define o algoritmo de assinatura com a nossa senha secreta
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            
            return JWT.create()
                .withIssuer("Internet Banking API") // Emissor do token [cite: 34]
                .withSubject(user.getEmail())       // Identifica o utilizador (email) [cite: 34]
                .withExpiresAt(dataExpiracao())     // Define o tempo de expiração [cite: 34]
                // .withClaim("id", user.getId())   // Pode adicionar outras informações se precisar [cite: 39]
                .sign(algoritmo); // Assina o token [cite: 34]
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        // Token expira em 2 horas [cite: 36, 37]
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}