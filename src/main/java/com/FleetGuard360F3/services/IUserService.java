package com.FleetGuard360F3.services;

import com.FleetGuard360F3.domain.entities.User;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public interface IUserService {
    public Optional<User> signupUser(String email, String phoneNumber);

    // public Optional<Jwt> completeUserSignup(String token);

    // public void sendAuthenticationEmail(String email);
}
