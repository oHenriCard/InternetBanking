package com.finalproject.internet.banking.internetbanking.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name="usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String name;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CheckAccount account;

// CONSTRUCTORS
    public User() {
    }

    public User(Long id, String name, String cpf, String email, String password, CheckAccount account) {
        this.id       = id;
        this.name     = name;
        this.cpf      = cpf;
        this.email    = email;
        this.password = password;
        this.account  = account;
    }

    // public User(UserDTO userDTO) {
    //     this.name     = userDTO.getName();
    //     this.cpf      = userDTO.getCpf();
    //     this.email    = userDTO.getEmail();
    //     this.password = userDTO.getPassword();
    // }

// GETTERS N SETTERS
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public CheckAccount getAccount() {
        return this.account;
    }
    public void setAccount(CheckAccount account) {
        this.account = account;
    }

    

}
