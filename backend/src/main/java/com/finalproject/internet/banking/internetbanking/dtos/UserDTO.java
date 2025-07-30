package com.finalproject.internet.banking.internetbanking.dtos;



import com.finalproject.internet.banking.internetbanking.entities.User;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotBlank(message = "O nome é obrigatório!")
    private String name;
    @NotBlank(message = "O CPF é obrigatório!")
    @CPF
    private String cpf;
    @NotBlank(message = "O E-Mail é obrigatório!")
    @Email(message = "E-Mail inválido!")
    private String email;
    @NotBlank(message = "A senha é obrigatória!")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String password;
// CONSTRUCTORS
    public UserDTO() {
    }

    public UserDTO(User user) {
    this.name     = user.getName();
    this.cpf      = user.getCpf();
    this.email    = user.getEmail();
    this.password = user.getPassword();
    }

// GETTERS N SETTERS
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
}
