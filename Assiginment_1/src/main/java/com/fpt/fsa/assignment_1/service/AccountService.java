package com.fpt.fsa.assignment_1.service;

import com.fpt.fsa.assignment_1.model.request.LoginRequest;
import com.fpt.fsa.assignment_1.model.response.LoginResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    LoginResponse login(LoginRequest payload);

    LoginResponse register(LoginRequest payload);

    boolean checkPassword(String rawPassword, String encodedPassword);
}
