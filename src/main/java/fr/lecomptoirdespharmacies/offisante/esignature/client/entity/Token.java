package fr.lecomptoirdespharmacies.offisante.esignature.client.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class Token {

    private static Duration VALIDITY_MINIMUM_DELAY = Duration.ofSeconds(30);

    private final DecodedJWT decodedJWT;

    public Token(String token) {
        this.decodedJWT = JWT.decode(token);
    }

    public String getToken() {
        return decodedJWT.getToken();
    }

    public boolean isExpired() {
        return decodedJWT
                .getExpiresAtAsInstant()
                .isBefore(
                        Instant.now().plus(VALIDITY_MINIMUM_DELAY)
                );
    }

}
