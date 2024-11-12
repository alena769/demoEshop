package com.example.demoeshop.general.controllers;

import com.example.demoeshop.general.model.User;
import com.example.demoeshop.general.services.user.UserServiceImp;
import com.example.demoeshop.shared.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Validated
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserServiceImp userService;
    @PostMapping("/register")
    @Operation(
            summary = "Registers a new user",
            description = "This endpoint registers a new user"
    )
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }
    @PostMapping("/login")
    @Operation(
            summary = "Authenticates a user",
            description = "This endpoint verifies a user's username and password"
    )
    public ResponseEntity<String> loginUser(
            @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticate(loginRequest));
    }
}

