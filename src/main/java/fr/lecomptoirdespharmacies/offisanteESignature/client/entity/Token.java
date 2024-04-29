package fr.lecomptoirdespharmacies.offisanteESignature.client.entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class Token {
    private final String token;
    private final LocalDateTime createdAt;
    private final Duration duration;

    public Token(String token, Duration duration) {
        this.token = token;
        this.createdAt = LocalDateTime.now();
        this.duration = duration;
    }

    public String getToken() {
        return token;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(createdAt.plus(duration));
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token.substring(0, 32) + "..." +
                ", createdAt=" + createdAt +
                ", duration=" + duration +
                '}';
    }
}
