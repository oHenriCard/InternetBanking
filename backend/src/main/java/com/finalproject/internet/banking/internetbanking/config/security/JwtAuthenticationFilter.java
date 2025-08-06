package com.finalproject.internet.banking.internetbanking.config.security;

import com.finalproject.internet.banking.internetbanking.repositories.UserRepository;
import com.finalproject.internet.banking.internetbanking.services.JWTokenService;
import com.finalproject.internet.banking.internetbanking.services.TokenDenyListService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenDenyListService tokenDenyListService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
                                    throws ServletException, IOException {       
        String tokenJWT = recoverToken(request);

        if (tokenJWT != null) {
            if (tokenDenyListService.isTokenDenied(tokenJWT)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            String subject = tokenService.getSubject(tokenJWT);
            UserDetails user = userRepository.findByEmail(subject).orElse(null);

            if (user != null) {
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer "))
            return authHeader.replace("Bearer ", "");
        return null;
    }
}