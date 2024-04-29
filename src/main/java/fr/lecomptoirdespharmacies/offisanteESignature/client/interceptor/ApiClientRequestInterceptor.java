package fr.lecomptoirdespharmacies.offisanteESignature.client.interceptor;

import feign.RequestInterceptor;
import fr.lecomptoirdespharmacies.offisanteESignature.client.service.LoginService;

public class ApiClientRequestInterceptor implements RequestInterceptor {
    private final LoginService loginService;

    public ApiClientRequestInterceptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void apply(feign.RequestTemplate template) {
        template.header("x-access-token", loginService.getValidToken());
    }
}
