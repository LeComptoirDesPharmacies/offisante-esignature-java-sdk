package fr.lecomptoirdespharmacies.offisante.esignature.client.service;

import fr.lecomptoirdespharmacies.offisante.esignature.ApiClient;
import fr.lecomptoirdespharmacies.offisante.esignature.api.AuthenticationApi;
import fr.lecomptoirdespharmacies.offisante.esignature.model.ApiResponse;
import fr.lecomptoirdespharmacies.offisante.esignature.model.LoginRequest;
import fr.lecomptoirdespharmacies.offisante.esignature.model.ValidTokenResponse;
import fr.lecomptoirdespharmacies.offisante.esignature.client.entity.Token;
import fr.lecomptoirdespharmacies.offisante.esignature.client.repository.TokenRepository;

import java.util.Objects;

public class LoginService {
    private final LoginRequest loginRequest;
    private final ApiClient apiClient;
    private final TokenRepository tokenRepository;

    public LoginService(LoginRequest loginRequest, ApiClient apiClient) {
        this.loginRequest = loginRequest;
        this.apiClient = apiClient;
        this.tokenRepository = new TokenRepository();
    }

    /**
     * Get a valid token (non expired): from the repository or create a new one if needed (expired or null)
     * This method is thread safe, only one thread can getValidToken at a time
     *
     * @return a valid token
     */
    public synchronized String getValidToken(){
        Token token = tokenRepository.findToken();

        // If token is null or expired, create a new token
        if(Objects.isNull(token) || token.isExpired()) {
            token = login();
        }

        return token.getToken();
    }

    /**
     * Generate a new token and save it in the repository
     *
     * @return a new valid token
     */
    private Token login(){
        AuthenticationApi authApi = apiClient.buildClient(AuthenticationApi.class);
        ApiResponse<ValidTokenResponse> response = authApi.loginWithHttpInfo(loginRequest);

        Token newToken = new Token(
                response.getData().getToken(),
                TokenRepository.DEFAULT_TOKEN_DURATION
        );

        tokenRepository.save(newToken);

        return newToken;
    }

    /**
     * Reset a token
     */
    public synchronized void resetToken() {
        tokenRepository.save(null);
    }
}
