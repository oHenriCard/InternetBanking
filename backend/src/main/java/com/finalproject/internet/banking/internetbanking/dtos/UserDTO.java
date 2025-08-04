package com.finalproject.internet.banking.internetbanking.dtos;



import com.finalproject.internet.banking.internetbanking.entities.User;

import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
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
}
