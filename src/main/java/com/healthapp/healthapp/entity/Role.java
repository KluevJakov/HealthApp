package com.healthapp.healthapp.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@EqualsAndHashCode
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String displayName; //отображаемое в UI название роли
    private String name; //системное название роли

    @Override
    public String getAuthority() {
        return name;
    }
}