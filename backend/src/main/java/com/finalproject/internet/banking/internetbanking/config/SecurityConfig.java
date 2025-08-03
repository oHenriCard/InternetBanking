package com.finalproject.internet.banking.internetbanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Import necessário
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                   .sessionManagement(sess ->
                                    sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                   // Adiciona a configuração de autorização de requisições
                   .authorizeHttpRequests(auth -> auth
                        // Permite acesso público ao endpoint de login
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        // Permite acesso público aos endpoints do Swagger
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET , "/usuarios").permitAll()
                        
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Exige autenticação para todas as outras requisições
                        .anyRequest().authenticated()
                   )
                   .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Bean para o Hashing de Senhas com BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}