package com.finalproject.internet.banking.internetbanking.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.internet.banking.internetbanking.dtos.UserDTO;
import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.entities.User;
import com.finalproject.internet.banking.internetbanking.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailNotificationService emailNotificationService;
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(UserDTO userDTO) {
        if(userRepository.findByCpf(userDTO.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado!");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        //user.setPasswordHash(password);
        user.setPassword(userDTO.getPassword());
        // user.setRegDate(LocalDateTime.now());

        CheckAccount account = new CheckAccount();
        account.setAccountNum(UUID.randomUUID().toString().substring(0, 8).replaceAll("\\D", ""));
        account.setBranch("0001");
        account.setBalance(BigDecimal.ZERO);
        account.setUser(user);

        user.setAccount(account);
        User savedUser = userRepository.save(user);

        emailNotificationService.sendWelcomeEmail(savedUser);

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
