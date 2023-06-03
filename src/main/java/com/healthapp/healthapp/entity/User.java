package com.healthapp.healthapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 3, allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String about;
    private String edu; //образование
    private String prof; //специализация
    private String password;
    private String avatar; //ссылка на аватарку
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    //методы переопределенные из UserDetails
    //для функционирования стандартной реализации авторизации
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return name;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
