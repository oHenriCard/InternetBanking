// Crie este novo arquivo em um pacote como 'com.finalproject.internet.banking.internetbanking.config.security'
package com.finalproject.internet.banking.internetbanking.config.security;

import com.finalproject.internet.banking.internetbanking.repositories.UserRepository;
import com.finalproject.internet.banking.internetbanking.services.JWTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Anotação para que o Spring possa injetá-lo
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String tokenJWT = recoverToken(request);

        if (tokenJWT != null) {
            // Se houver um token, valida-o e autentica o usuário
            String subject = tokenService.getSubject(tokenJWT); // Você precisará criar este método no seu JWTokenService
            UserDetails user = userRepository.findByEmail(subject).orElse(null);

            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response); // Continua para o próximo filtro na cadeia
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}