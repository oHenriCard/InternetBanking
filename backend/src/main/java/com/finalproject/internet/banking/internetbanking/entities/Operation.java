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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Operation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
