package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.LoginDTO;
import com.FleetGuard360F3.DTO.SignupDTO;
import com.FleetGuard360F3.domain.entities.User;
import com.FleetGuard360F3.services.IJWTService;
import com.FleetGuard360F3.services.IUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IUserService userService;
    private final IJWTService jwtService;

    public AuthController(IUserService userService, IJWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<Optional<SignupDTO>> signup(@RequestBody SignupDTO signupDTO) {
        try {
            Optional<User> createdUser = userService.signupUser(signupDTO.getEmail(), signupDTO.getPhoneNumber());

            if (createdUser.isPresent()) {
                return ResponseEntity.ok().body(Optional.of(signupDTO));
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where the user already exists
            return ResponseEntity.badRequest().body(Optional.empty());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "signup/complete", method = RequestMethod.GET)
    public ResponseEntity<String> completeSignup(@RequestParam String token) {
        Optional<Jwt> authToken = userService.completeUserSignup(token);

        if (authToken.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        // Send the authToken back in a cookie
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "FleetGuard-Auth-Token=" + authToken.get().getTokenValue() + "; HttpOnly; Secure; SameSite=Strict; Path=/");

        return ResponseEntity.ok().headers(headers).body("Signup completed successfully.");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            Optional<Jwt> user = userService.loginUser(loginDTO.getEmail());

            if (user.isPresent()) {
                Jwt authToken = jwtService.generateLoginToken(loginDTO.getEmail());

                // Send the authToken back in a cookie
                HttpHeaders headers = new HttpHeaders();
                headers.add("Set-Cookie", "FleetGuard-Auth-Token=" + authToken.getTokenValue() + "; HttpOnly; Secure; SameSite=Strict; Path=/");

                return ResponseEntity.ok().headers(headers).body("Login successful.");
            } else {
                return ResponseEntity.badRequest().body("Invalid email or phone number.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login failed: " + e.getMessage());
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout() {
        // Invalidate the session or token here if necessary
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "FleetGuard-Auth-Token=; HttpOnly; Secure; SameSite=Strict; Max-Age=0");

        return ResponseEntity.ok().headers(headers).body("Logout successful.");
    }


    @RequestMapping(value = "validate", method = RequestMethod.GET)
    public ResponseEntity<String> validateToken(@CookieValue (value = "FleetGuard-Auth-Token", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).body("Unauthorized: No token provided.");
        }

        Optional<Jwt> validatedToken = jwtService.validateToken(token);

        if (validatedToken.isEmpty()) {
            return ResponseEntity.status(401).body("Unauthorized: Invalid or expired token.");
        }

        // If the token is valid, return a success message
        return ResponseEntity.ok("Token is valid for user: " + validatedToken.get().getSubject());
    }
}
