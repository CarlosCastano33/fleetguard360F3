package com.FleetGuard360F3.Controller;

import com.FleetGuard360F3.DTO.LoginDTO;
import com.FleetGuard360F3.DTO.MessageDTO;
import com.FleetGuard360F3.DTO.SignupDTO;
import com.FleetGuard360F3.domain.entities.User;
import com.FleetGuard360F3.services.IJWTService;
import com.FleetGuard360F3.services.IUserService;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${app.base.url}")
    private String appBaseUrl;

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
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(value = "signup/complete", method = RequestMethod.GET)
    public ResponseEntity<MessageDTO> completeSignup(@RequestParam String token) {
        Optional<Jwt> authToken = userService.completeUserSignup(token);

        if (authToken.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageDTO(null, "Invalid or expired token."));
        }

        // Send the authToken back in a cookie and redirect to app.base.url
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "FleetGuard-Auth-Token=" + authToken.get().getTokenValue() + "; HttpOnly; Secure; SameSite=Strict; Path=/");
        headers.add("Location", appBaseUrl);
        return ResponseEntity.status(302).headers(headers).body(MessageDTO.withMessage("Signup complete"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<MessageDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            Optional<String> mailResult = userService.loginUser(loginDTO.getEmail());

            if (mailResult.isPresent()) {
                return ResponseEntity.ok().body(MessageDTO.withMessage("Login successful, check your email."));
            } else {
                return ResponseEntity.badRequest().body(MessageDTO.withError("Invalid email or phone number."));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(MessageDTO.withError("Login failed: " + e.getMessage()));
        }
    }

    @RequestMapping(value = "login/complete", method = RequestMethod.GET)
    public ResponseEntity<MessageDTO> completeLogin(@RequestParam String token) {
        Optional<Jwt> authToken = jwtService.validateToken(token);

        if (authToken.isEmpty()) {
            return ResponseEntity.badRequest().body(MessageDTO.withError("Invalid or expired token."));
        }

        // Send the authToken back in a cookie and redirect to app.base.url
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "FleetGuard-Auth-Token=" + authToken.get().getTokenValue() + "; HttpOnly; Secure; SameSite=Strict; Path=/");
        headers.add("Location", appBaseUrl);
        return ResponseEntity.status(302).headers(headers).body(MessageDTO.withMessage("Login complete"));
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ResponseEntity<MessageDTO> logout() {
        // Invalidate the session or token here if necessary
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "FleetGuard-Auth-Token=; HttpOnly; Secure; SameSite=Strict; Path=/; Max-Age=0");

        return ResponseEntity.ok().headers(headers).body(MessageDTO.withMessage("Logout successful."));
    }


    @RequestMapping(value = "validate", method = RequestMethod.GET)
    public ResponseEntity<MessageDTO> validateToken(@CookieValue(value = "FleetGuard-Auth-Token", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(401).body(MessageDTO.withError("Unauthorized: No token provided."));
        }

        Optional<Jwt> validatedToken = jwtService.validateToken(token);

        if (validatedToken.isEmpty()) {
            return ResponseEntity.status(401).body(MessageDTO.withError("Unauthorized: Invalid or expired token."));
        }

        // If the token is valid, return a success message
        return ResponseEntity.ok(MessageDTO.withMessage("Token is valid for user: " + validatedToken.get().getSubject()));
    }
}
