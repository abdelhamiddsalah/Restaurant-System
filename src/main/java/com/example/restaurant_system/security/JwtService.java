
package com.example.restaurant_system.security;

import com.example.restaurant_system.user.entity.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private static final long ACCESS_TOKEN_TTL_SECONDS = 900;

    private final SecretKey jwtSecretKey;

    public JwtService(SecretKey jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(
                ACCESS_TOKEN_TTL_SECONDS
        );

        List<String> roles = user.getRoles()
                .stream()
                .map(Enum::name)
                .toList();

        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(jwtSecretKey)
                .compact();
    }
}
