package com.example.demoeshop.general.controllers;

import com.example.demoeshop.general.dto.UserDTO;
import com.example.demoeshop.general.model.User;
import com.example.demoeshop.general.services.user.UserServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User", description = "Endpoints for managing user registration and related operations")
public class UserController {
    private final UserServiceImp userService;

    @PostMapping("/register")
    @Operation(
            summary = "Registers a new user",
            description = "This endpoint registers a new user by providing necessary user information."
    )
    public ResponseEntity<UserDTO> registerUser(
            @Parameter(description = "User object containing registration details", required = true)
            @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok(UserDTO.from(user));
    }
}
