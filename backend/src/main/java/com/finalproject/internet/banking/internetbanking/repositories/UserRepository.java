package com.finalproject.internet.banking.internetbanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finalproject.internet.banking.internetbanking.entities.User;

public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByCpf(String cpf);
    Optional<User> findByEmail(String email);
}
