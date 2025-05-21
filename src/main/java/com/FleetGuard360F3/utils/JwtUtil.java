package com.FleetGuard360F3.utils;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;

import java.util.Date;

@Component
public class JwtUtil {
    private final String secret = "superSecretsuperSecretsuperSecretsuperSecretsuperSecretsuperSecretsuperSecretsuperSecretsuperSecret";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256) // correcta con 0.11.5
                .compact();
    }

    public String getEmailFromToken(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // ✅ Usa la misma clave
                .build();
        return parser.parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

