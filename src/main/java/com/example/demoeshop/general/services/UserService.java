package com.example.demoeshop.general.services;

import com.example.demoeshop.general.model.User;
import com.example.demoeshop.general.repositories.UserRepository;
import com.example.demoeshop.shared.LoginRequest;
import com.example.demoeshop.shared.exeption.AuthenticationException;
import com.example.demoeshop.shared.exeption.UsernameAlreadyExistsException;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken.");
        }
        return userRepository.save(user);
    }

    public String authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AuthenticationException("Invalid username or password"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return "Authentication successful";
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }
}

