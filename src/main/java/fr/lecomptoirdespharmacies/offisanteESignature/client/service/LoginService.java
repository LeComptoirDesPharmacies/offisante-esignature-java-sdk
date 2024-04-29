package fr.lecomptoirdespharmacies.offisanteESignature.client.service;

import fr.lecomptoirdespharmacies.offisanteESignature.ApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.api.AuthenticationApi;
import fr.lecomptoirdespharmacies.offisanteESignature.model.ApiResponse;
import fr.lecomptoirdespharmacies.offisanteESignature.model.LoginRequest;
import fr.lecomptoirdespharmacies.offisanteESignature.model.ValidTokenResponse;
import fr.lecomptoirdespharmacies.offisanteESignature.client.entity.Token;
import fr.lecomptoirdespharmacies.offisanteESignature.client.repository.TokenRepository;


public class LoginService {
    private final LoginRequest loginRequest;
    private final ApiClient apiClient;
    private final TokenRepository tokenRepository;

    public LoginService(LoginRequest loginRequest, ApiClient apiClient, TokenRepository tokenRepository) {
        this.loginRequest = loginRequest;
        this.apiClient = apiClient;
        this.tokenRepository = tokenRepository;
    }

    public Token login(){
        AuthenticationApi authApi = apiClient.buildClient(AuthenticationApi.class);
        ApiResponse<ValidTokenResponse> response = authApi.loginWithHttpInfo(loginRequest);
        tokenRepository.save(new Token(
                response.getData().getToken(),
                TokenRepository.DEFAULT_TOKEN_DURATION
        ));

        return tokenRepository.findToken();
    }
}
