package com.dsm.authservice.controller;
import com.dsm.authservice.dto.ApiResponse;
import com.dsm.authservice.dto.AuthRequest;
import com.dsm.authservice.model.UserCredential;
import com.dsm.authservice.service.AuthService;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public ApiResponse<String> addNewUser(@RequestBody UserCredential user) {
        String result = service.saveUser(user);
        return new ApiResponse<>(true, "User registered successfully", result);
    }

    @PostMapping("/login")
    public ApiResponse<String> getToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authenticate.isAuthenticated()) {
                String token = service.generateToken(authRequest.getUsername());
                return new ApiResponse<>(true, "Login successful", token);
            } else {
                throw new RuntimeException("Invalid access");
            }
        } catch (Exception e) {
            return new ApiResponse<>(false, e.getMessage(), null);
        }
    }

    @GetMapping("/validate")
    public ApiResponse<String> validateToken(@RequestParam("token") String token) {
        try {
            service.validateToken(token);
            return new ApiResponse<>(true, "Token is valid", null);
        } catch (Exception e) {
            return new ApiResponse<>(false, e.getMessage(), null);
        }
    }
}
