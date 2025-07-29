package com.finalproject.internet.banking.internetbanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.repositories.CheckAccountRepository;

@Service
public class CheckAccountService {
    @Autowired
    private CheckAccountRepository accountRepository;

    public CheckAccountService (CheckAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public CheckAccount getByNum(String accountNum) {
        return accountRepository.findByAccountNum(accountNum)
            .orElseThrow(() -> new IllegalArgumentException("Conta com o número " + accountNum + " não encontrada."));
    }
}
