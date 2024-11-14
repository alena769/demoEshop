package com.example.demoeshop.general.services.user;

import com.example.demoeshop.general.model.User;
import com.example.demoeshop.general.repositories.UserRepository;
import com.example.demoeshop.general.LoginRequest;
import com.example.demoeshop.general.exeption.AuthenticationException;
import com.example.demoeshop.general.exeption.UsernameAlreadyExistsException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username '" + user.getUsername() + "' is already taken.");
        }
        userRepository.save(user);
        return user;
    }

    @Override
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

