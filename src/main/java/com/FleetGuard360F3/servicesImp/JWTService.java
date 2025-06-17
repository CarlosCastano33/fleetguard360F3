package com.FleetGuard360F3.servicesImp;

import com.FleetGuard360F3.services.IJWTService;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@Service
public class JWTService implements IJWTService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public Jwt generateToken(String subject, String type, Map<String, Object> claims, long expirationSeconds) {
        Instant now = Instant.now();
        JwtClaimsSet.Builder claimsBuilder = JwtClaimsSet
                .builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationSeconds))
                .claim("type", type);

        if (claims != null) {
            claims.forEach(claimsBuilder::claim);
        }
        JwtClaimsSet claimsSet = claimsBuilder.build();

        var encoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claimsSet);
        return jwtEncoder.encode(encoderParameters);
    }

    @Override
    public Jwt generateSignupToken(String subject) {
        return generateToken(subject, "signup", null, 3600 * 4);
    }

    public Jwt generateLoginToken(String subject) {
        return generateToken(subject, "login", null, 3600 * 4);
    }

    public Optional<Jwt> validateToken(String token) {
        try {
            return Optional.of(jwtDecoder.decode(token));
        } catch (JwtException e) {
            return Optional.empty();
        }
    }
}
