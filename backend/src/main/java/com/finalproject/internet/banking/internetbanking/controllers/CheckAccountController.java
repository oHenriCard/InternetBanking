package com.finalproject.internet.banking.internetbanking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.dtos.CheckAccountDTO;
import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.services.CheckAccountService;

@RestController
@RequestMapping("/contas")
public class CheckAccountController {
    @Autowired
    private CheckAccountService accountService;

    @GetMapping
    public ResponseEntity<List<CheckAccountDTO>> getAllAccounts() {
        List<CheckAccount> accounts = accountService.getAllAccounts();
        List<CheckAccountDTO> accountDTOs = accounts.stream()
                                                    .map(CheckAccountDTO::new)
                                                    .toList();
        return ResponseEntity.ok(accountDTOs);
    }


}
