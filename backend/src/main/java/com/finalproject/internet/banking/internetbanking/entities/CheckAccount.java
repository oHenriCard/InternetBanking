package com.finalproject.internet.banking.internetbanking.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name="CheckAccount")
public class CheckAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long       id;
    private String     accountNum; 
    private String     branch;
    private BigDecimal balance;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    //TODO @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    // private List<Operation> operations;
// CONSTRUCTOR
    public CheckAccount() {
    }
    public CheckAccount(Long id, String accountNum, String branch, BigDecimal balance, User user) {
        this.id         = id;
        this.accountNum = accountNum;
        this.branch     = branch;
        this.balance    = balance;
        this.user       = user;
    }

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
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
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

    //TODO public List<Operation> getOperations() {
    //     return this.operations;
    // }
    // public void setOperations(List<Operation> operations) {
    //     this.operations = operations;
    // }


}
