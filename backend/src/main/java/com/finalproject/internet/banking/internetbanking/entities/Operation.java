package com.finalproject.internet.banking.internetbanking.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long          id;
    @Enumerated (EnumType.STRING)
    private type          type;
    private BigDecimal    value;
    private LocalDateTime dateTime;
    private String        description;
    @ManyToOne
    @JoinColumn(name = "check_account_id", referencedColumnName = "id")
    private CheckAccount account;

    public enum type {
        DEPOSIT, WITHDRAW, PAYMENT, STATEMENT
    }

// GETTERS N SETTERS
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public type getType() {
        return this.type;
    }
    public void setType(type type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return this.value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public CheckAccount getAccount() {
        return this.account;
    }
    public void setAccount(CheckAccount account) {
        this.account = account;
    }
}
