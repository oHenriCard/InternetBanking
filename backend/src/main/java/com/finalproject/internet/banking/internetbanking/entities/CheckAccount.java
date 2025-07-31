package com.finalproject.internet.banking.internetbanking.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="CheckAccount")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
