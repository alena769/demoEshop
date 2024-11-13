package com.example.demoeshop.general.model;

import com.example.demoeshop.general.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseEntity<Long> {

    @NotNull
    private String username;

    @NotNull
    private transient String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public enum RoleType {
        USER,
        ADMIN
    }
}

