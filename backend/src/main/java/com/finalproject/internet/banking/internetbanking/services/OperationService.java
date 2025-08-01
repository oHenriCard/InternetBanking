package com.finalproject.internet.banking.internetbanking.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.internet.banking.internetbanking.dtos.OperationDTO;
import com.finalproject.internet.banking.internetbanking.entities.CheckAccount;
import com.finalproject.internet.banking.internetbanking.entities.Operation;
import com.finalproject.internet.banking.internetbanking.repositories.CheckAccountRepository;
import com.finalproject.internet.banking.internetbanking.repositories.OperationRepository;

import jakarta.transaction.Transactional;

@Service
public class OperationService {
    @Autowired
    private CheckAccountRepository accountRepository;
    @Autowired
    private OperationRepository    operationRepository;
    //TODO @Autowired
    //TODO private EmailNotificationService emailNotificationService;
// METHODS
    // Deposit
    @Transactional
    public CheckAccount deposit(String accountNum, BigDecimal value) {
        CheckAccount account = accountRepository.findByAccountNum(accountNum)
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        if(value.compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("O valor do depósito precisa ser maior que zero.");
        
        account.setBalance(account.getBalance().add(value));
        registerOperation(account, Operation.type.DEPOSIT, value, "Deposito em conta.");

        CheckAccount updatedAccount = accountRepository.save(account);
        //TODO emailNotificationService.sendTransactionEmail(updatedAccount.getUser(), "Depósito", value, updatedAccount.getBalance());
        return updatedAccount;
    }
    // Withdraw
    @Transactional
    public CheckAccount withdraw(String accountNum, BigDecimal value) {
        CheckAccount account = accountRepository.findByAccountNum(accountNum)
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        if(value.compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("O valor do saque precisa ser maior que zero.");
        account.setBalance(account.getBalance().subtract(value));
        registerOperation(account, Operation.type.WITHDRAW, value, "Saque de conta.");
        
        CheckAccount updatedAccount = accountRepository.save(account);
        //TODO emailNotificationService.sendTransactionEmail(updatedAccount.getUser(), "Saque", value, updatedAccount.getBalance());
        return updatedAccount;
    }

    // Payment
    @Transactional
    public CheckAccount payment(String accountNum, BigDecimal value, String description) {
        CheckAccount account = accountRepository.findByAccountNum(accountNum)
            .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
        if(value.compareTo(BigDecimal.ZERO) <= 0) 
            throw new IllegalArgumentException("O valor do pagamento precisa ser maior que zero.");
        if(account.getBalance().compareTo(value) < 0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar o pagamento.");
        
        account.setBalance(account.getBalance().subtract(value));
        registerOperation(account, Operation.type.PAYMENT, value, description);

        CheckAccount updatedAccount = accountRepository.save(account);
        //TODO emailNotificationService.sendTransactionEmail(updatedAccount.getUser(), "Pagamento (" + description + ")", value, updatedAccount.getBalance());
        return updatedAccount;
    }

    // Statement
    public List<OperationDTO> getStatement(String accountNum, LocalDate startDate, LocalDate endDate) {
        List<Operation> operations = operationRepository.findByAccountAccountNumOrderByDateTimeDesc(accountNum);
        return operations.stream()
                .filter(op -> (startDate == null || op.getDateTime().toLocalDate().isAfter(startDate.minusDays(1))))
                .filter(op -> (endDate == null || op.getDateTime().toLocalDate().isBefore(endDate.plusDays(1))))
                .map(OperationDTO::new)
                .collect(Collectors.toList());
    }
    
    // RegisterOperation
    private void registerOperation(CheckAccount account, Operation.type type, BigDecimal value, String description) {
        Operation operation = new Operation();
        operation.setAccount(account);
        operation.setType(type);
        operation.setValue(value);
        operation.setDescription(description);
        operation.setDateTime(LocalDateTime.now());
        operationRepository.save(operation);
    }
}
