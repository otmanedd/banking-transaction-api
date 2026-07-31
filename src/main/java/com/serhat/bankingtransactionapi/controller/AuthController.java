package com.serhat.bankingtransactionapi.controller;

import com.serhat.bankingtransactionapi.dto.LoginRequest;
import com.serhat.bankingtransactionapi.dto.RegisterRequest;
import com.serhat.bankingtransactionapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}