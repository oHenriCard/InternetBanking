package com.finalproject.internet.banking.internetbanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;

@Repository
public interface CheckAccountRepository extends JpaRepository<CheckAccount, Long> {
    Optional<CheckAccount> findByAccountNum(String accountNum);

}
