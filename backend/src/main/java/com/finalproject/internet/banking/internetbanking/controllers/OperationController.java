package com.finalproject.internet.banking.internetbanking.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.internet.banking.internetbanking.dtos.DepositDTO;
import com.finalproject.internet.banking.internetbanking.dtos.OperationDTO;
import com.finalproject.internet.banking.internetbanking.dtos.PaymentDTO;
import com.finalproject.internet.banking.internetbanking.dtos.WithdrawDTO;
import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.services.OperationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/operacoes")
public class OperationController {
    @Autowired
    private OperationService operationService;

    // Deposit
    @PostMapping("/deposito")
    @Operation(summary = "Realiza um deposito em uma conta.")
    public ResponseEntity<CheckAccount> deposit(@Valid @RequestBody DepositDTO depositDTO) {
        CheckAccount updatedAccount = operationService.deposit(depositDTO.getAccountNum(), depositDTO.getValue());
        return ResponseEntity.ok(updatedAccount);
    }

    // WithDraw
    @PostMapping("/saque")
    @Operation(summary = "Realiza um saque na conta.")
    public ResponseEntity<CheckAccount> withdraw(@Valid @RequestBody WithdrawDTO withDrawDTO) {
        CheckAccount updatedAccount = operationService.withdraw(withDrawDTO.getAccountNum(), withDrawDTO.getValue());
        return ResponseEntity.ok(updatedAccount);
    }

    // Payment
    @PostMapping("/pagamento")
    @Operation(summary = "Realiza um pagamento a partir de uma conta.")
    public ResponseEntity<CheckAccount> payment(@Valid @RequestBody PaymentDTO paymentDTO) {
        CheckAccount updatedAccount = operationService.payment(paymentDTO.getAccountNum(), paymentDTO.getValue(),
                paymentDTO.getDescription());
        return ResponseEntity.ok(updatedAccount);
    }

    // Statement
    @GetMapping("/extrato/{accountNum}")
    public ResponseEntity<List<OperationDTO>> getStatement(
            @PathVariable String accountNum,
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        
        List<OperationDTO> statement = operationService.getStatement(accountNum, dataInicio, dataFim);
        return ResponseEntity.ok(statement);
    }
}
