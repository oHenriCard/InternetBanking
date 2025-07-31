package com.finalproject.internet.banking.internetbanking.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO Define as "roles" (permissões) do utilizador.
        //TODO Por agora, todos são ROLE_USER.
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email; //TODO O email será usado como login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta não expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta não está bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais não expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // O utilizador está ativo
    }

}
