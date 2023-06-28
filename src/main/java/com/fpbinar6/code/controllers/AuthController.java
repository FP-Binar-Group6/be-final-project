package com.fpbinar6.code.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fpbinar6.code.services.AuthService;
import com.fpbinar6.code.auth.AuthenticationRequest;
import com.fpbinar6.code.auth.AuthenticationResponse;
import com.fpbinar6.code.auth.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;
    
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }
}
