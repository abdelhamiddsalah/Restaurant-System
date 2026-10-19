package com.example.restaurant_system.user.dto;

public record AuthResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
