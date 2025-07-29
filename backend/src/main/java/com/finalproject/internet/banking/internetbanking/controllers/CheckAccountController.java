package com.finalproject.internet.banking.internetbanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.services.CheckAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/contas")
// @CrossOrigin(origins = "http://localhost:3000")
public class CheckAccountController {
    @Autowired
    private CheckAccountService accountService;

    @GetMapping("/{accountNum}")
    @Operation(summary = "Busca os detalhes de uma conta corrente pelo número")
    public ResponseEntity<CheckAccount> getAccountByNum(
        @Parameter(description = "Número da conta a ser consultada") @PathVariable String accountNum
    ) {
        CheckAccount account = accountService.getByNum(accountNum);
        return ResponseEntity.ok(account);
    }

}
