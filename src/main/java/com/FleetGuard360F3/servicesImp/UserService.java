package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.domain.entities.User;
import com.FleetGuard360F3.domain.repository.IUserRepository;
import com.FleetGuard360F3.services.IEmailService;
import com.FleetGuard360F3.services.IJWTService;
import com.FleetGuard360F3.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IEmailService emailService;
    private final IJWTService jwtService;

    @Autowired
    public UserService(IUserRepository userRepository, IEmailService emailService, IJWTService jwtService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    @Override
    public Optional<User> signupUser(String email, String phoneNumber) {
        Optional<User> user = userRepository.findUserByEmail(email).or(() -> userRepository.findUserByPhoneNumber(phoneNumber));

        if (user.isPresent()) {
            if (user.get().getIsActive()) {
                Jwt loginToken = jwtService.generateLoginToken(email);
                URI loginLink = URI.create("https://localhost:8080/auth/login?token=" + loginToken.getTokenValue());

                emailService.sendLoginEmail(email, loginLink);
                return user;
            }

            user.get().setEmail(email);
            user.get().setPhoneNumber(phoneNumber);
            userRepository.save(user.get());
        } else {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPhoneNumber(phoneNumber);
            userRepository.save(newUser);
        }

        Jwt signupToken = jwtService.generateSignupToken(email);
        URI signupConfirmationLink = URI.create("https://localhost:8080/api/auth/signup/complete?token=" + signupToken.getTokenValue());

        emailService.sendSignupEmail(email, signupConfirmationLink);

        return Optional.of(new User());
    }

    @Override
    public Optional<Jwt> completeUserSignup(String token) {
        Optional<Jwt> validatedToken = jwtService.validateToken(token);

        if (validatedToken.isEmpty()) {
            return Optional.empty();
        }

        Optional<User> user = userRepository.findUserByEmail(validatedToken.get().getSubject());

        if (user.isEmpty()) {
            return Optional.empty();
        }

        user.get().setIsActive(true);
        userRepository.save(user.get());

        Jwt authToken = jwtService.generateLoginToken(validatedToken.get().getSubject());
        return Optional.of(authToken);
    }

    @Override
    public Optional<String> loginUser(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty() || !user.get().getIsActive()) {
            return Optional.empty();
        }

        Jwt authToken = jwtService.generateLoginToken(email);
        URI loginConfirmationLink = URI.create("https://localhost:8080/api/auth/login/complete?token=" + authToken.getTokenValue());
        emailService.sendLoginEmail(email, loginConfirmationLink);

        return Optional.of(email);
    }
}
