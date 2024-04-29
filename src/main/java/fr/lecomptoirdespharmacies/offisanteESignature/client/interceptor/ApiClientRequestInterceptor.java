package fr.lecomptoirdespharmacies.offisanteESignature.client.interceptor;

import feign.RequestInterceptor;
import fr.lecomptoirdespharmacies.offisanteESignature.client.service.LoginService;
import fr.lecomptoirdespharmacies.offisanteESignature.client.entity.Token;
import fr.lecomptoirdespharmacies.offisanteESignature.client.repository.TokenRepository;
import java.util.Objects;

public class ApiClientRequestInterceptor implements RequestInterceptor {
    private final TokenRepository tokenRepository;
    private final LoginService loginService;

    public ApiClientRequestInterceptor(TokenRepository tokenRepository, LoginService loginService) {
        this.tokenRepository = tokenRepository;
        this.loginService = loginService;
    }

    @Override
    public void apply(feign.RequestTemplate template) {
        template.header("x-access-token", getValidToken().getToken());
    }

    /**
     * Get a valid token (non expired)
     * This method is thread safe, only one thread can get a token at a time
     *
     * @return a valid token
     */
    private synchronized Token getValidToken(){
        Token token = tokenRepository.findToken();

        // If token is null or expired, create a new token
        if(Objects.isNull(token) || token.isExpired()) {
            token = loginService.login();
        }

        return token;
    }
}
