package com.finalproject.internet.banking.internetbanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.finalproject.internet.banking.internetbanking.repositories.UserRepository;

public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return userRepository.findByCpf(loginId)
                .orElseThrow(() -> 
                    new UsernameNotFoundException("Utilizador não encontrado com o email: " + loginId));
    }

}
