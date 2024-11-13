package com.example.demoeshop.general.model;

import com.example.demoeshop.shared.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseEntity<Long> {
    private String username;
    private transient String password;
    private String role;
}

