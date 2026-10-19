package com.example.restaurant_system.user.service;

import com.example.restaurant_system.security.JwtService;
import com.example.restaurant_system.user.dto.AuthResponse;
import com.example.restaurant_system.user.dto.LoginRequest;
import com.example.restaurant_system.user.entity.RegisterRequest;
import com.example.restaurant_system.user.entity.User;
import com.example.restaurant_system.user.repository.UserRepository;
import com.example.restaurant_system.user.role.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private static final long ACCESS_TOKEN_TTL_SECONDS = 900;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.username())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());

        // Never store the raw password in the database.
        user.setPassword(passwordEncoder.encode(request.password()));

        // Public registration always creates a normal USER.
        user.setRoles(Set.of(Role.USER));

        User savedUser = userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(savedUser);

        return new AuthResponse(
                accessToken,
                "Bearer",
                ACCESS_TOKEN_TTL_SECONDS
        );
    }

    public AuthResponse login(LoginRequest request) {

        // Spring Security checks the username and password.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid username or password"));

        String accessToken = jwtService.generateAccessToken(user);

        return new AuthResponse(
                accessToken,
                "Bearer",
                ACCESS_TOKEN_TTL_SECONDS
        );
    }
}
