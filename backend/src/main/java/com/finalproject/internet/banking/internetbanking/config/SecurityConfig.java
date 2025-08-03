package com.finalproject.internet.banking.internetbanking.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.finalproject.internet.banking.internetbanking.config.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
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
                        .requestMatchers(HttpMethod.GET,  "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/contas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/operacoes/**").permitAll()
                        .requestMatchers(HttpMethod.GET,  "/operacoes/**").permitAll()
                        
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        // Exige autenticação para todas as outras requisições
                        .anyRequest().authenticated()
                   )
                   .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Defina a URL do seu frontend. Use a porta correta!
        // Exemplos: "http://localhost:3000" (React), "http://localhost:5173" (Vite), "http://localhost:4200" (Angular)
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        
        // Permite todos os métodos HTTP padrão
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        // Permite todos os cabeçalhos. Importante para cabeçalhos como "Authorization" e "Content-Type"
        configuration.setAllowedHeaders(List.of("*"));
        
        // Permite que credenciais (como cookies) sejam enviadas, se aplicável
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica esta configuração de CORS a todas as rotas da sua API
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

    
}