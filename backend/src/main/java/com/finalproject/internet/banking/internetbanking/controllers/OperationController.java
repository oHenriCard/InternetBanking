package com.finalproject.internet.banking.internetbanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.dtos.DepositDTO;
import com.finalproject.internet.banking.internetbanking.dtos.PaymentDTO;
import com.finalproject.internet.banking.internetbanking.dtos.WithDrawDTO;
import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.services.OperationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operacoes")
// @CrossOrigin(origins = "http://localhost:3000")
public class OperationController {
    @Autowired
    private OperationService operationService;

    // Deposit
    @PostMapping("/deposito")
    @Operation(summary="Realiza um deposito em uma conta.")
    public ResponseEntity<CheckAccount> deposit(@Valid @RequestBody DepositDTO depositDTO) {
        CheckAccount updatedAccount = operationService.deposit(depositDTO.getAccountNum(), depositDTO.getValue());
        return ResponseEntity.ok(updatedAccount);
    }
    // WithDraw
    @PostMapping("/saque")
    @Operation(summary="Realiza um saque na conta.")
    public ResponseEntity<CheckAccount> withdraw(@Valid @RequestBody WithDrawDTO withDrawDTO) {
        CheckAccount updatedAccount = operationService.withdraw(withDrawDTO.getAccountNum(), withDrawDTO.getValue());
        return ResponseEntity.ok(updatedAccount);
    }
    // Payment
    @PostMapping("/pagamento")
    @Operation(summary="Realiza um pagamento a partir de uma conta.")
    public ResponseEntity<CheckAccount> payment(@Valid @RequestBody PaymentDTO paymentDTO) {
        CheckAccount updatedAccount = operationService.payment(paymentDTO.getAccountNum(), paymentDTO.getValue(), paymentDTO.getDescription());
        return ResponseEntity.ok(updatedAccount);
    }

}
