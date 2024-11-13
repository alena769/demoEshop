package com.example.demoeshop.general.services.user;

import com.example.demoeshop.general.model.User;
import com.example.demoeshop.general.LoginRequest;

public interface UserService {
    User registerUser(User user);
    String authenticate(LoginRequest loginRequest);
}
