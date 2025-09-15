package org.example.easybookbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.easybookbackend.domain.dto.AuthRequest;
import org.example.easybookbackend.domain.dto.AuthResponse;
import org.example.easybookbackend.domain.dto.UserDto;
import org.example.easybookbackend.service.serviceImpl.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            return ResponseEntity.status(401).build();
        }
        String email = authentication.getName();
        return ResponseEntity.ok(authService.currentUser(email));
    }
}