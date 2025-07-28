package com.finalproject.internet.banking.internetbanking.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name="contacorrente")
public class CheckAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;
    private String     accountNum; 
    private String     branch;
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Operation> operations;

// CONSTRUCTORS
    public CheckAccount() {
    }
    public CheckAccount(Long id, String accountNum, String branch, BigDecimal balance, User user) {
        this.id         = id;
        this.accountNum = accountNum;
        this.branch     = branch;
        this.balance    = balance;
        this.user       = user;
    }
    // public CheckAccount(CheckAccountDTO accountDTO) {
    //     this.id      = accountDTO.id();
    //     this.account = accountDTO.account();
    //     this.branch  = accountDTO.branch();
    //     this.balance = accountDTO.balance();
    //     this.user    = accountDTO.user();
    // }

// GETTERS N SETTERS
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNum() {
        return this.accountNum;
    }
    public void setAccountNum(String account) {
        this.accountNum = account;
    }

    public String getBranch() {
        return this.branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
