package fr.lecomptoirdespharmacies.offisante.esignature.client.repository;

import fr.lecomptoirdespharmacies.offisante.esignature.client.entity.Token;
import java.time.Duration;

public class TokenRepository {

    // On offisante, token duration is 86400 secondes (24 hours)
    public static final Duration DEFAULT_TOKEN_DURATION = Duration.ofHours(24);

    private Token token;

    public synchronized Token findToken() {
        return token;
    }

    public synchronized void save(Token token) {
        this.token = token;
    }
}
