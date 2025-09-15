package org.example.easybookbackend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.easybookbackend.config.JwtService;
import org.example.easybookbackend.domain.dto.AuthRequest;
import org.example.easybookbackend.domain.dto.AuthResponse;
import org.example.easybookbackend.domain.dto.UserDto;
import org.example.easybookbackend.repository.UserRepository;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthResponse login(AuthRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.email(), request.password());
        authenticationManager.authenticate(auth);

        var user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.generateToken(user);
        Instant exp = jwtService.getExpiration(token);
        return AuthResponse.bearer(token, exp);
    }

    public UserDto currentUser(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        return new UserDto(user.getId(), user.getEmail(), user.getFullName(), user.getRole(), user.isEnabled());
    }
}