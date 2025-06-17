package com.FleetGuard360F3.services;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Map;
import java.util.Optional;

public interface IJWTService {
    Jwt generateToken(String subject, String type, Map<String, Object> claims, long expirationSeconds);
    Jwt generateSignupToken(String subject);
    Jwt generateLoginToken(String subject);
    Optional<Jwt> validateToken(String token);
}
