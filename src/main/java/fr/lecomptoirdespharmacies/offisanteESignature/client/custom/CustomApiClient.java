package fr.lecomptoirdespharmacies.offisanteESignature.client.custom;

import feign.codec.ErrorDecoder;
import fr.lecomptoirdespharmacies.offisanteESignature.ApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.client.interceptor.ApiClientRequestInterceptor;
import fr.lecomptoirdespharmacies.offisanteESignature.client.service.LoginService;

public class CustomApiClient extends ApiClient {

    public static String DEV_URL = "https://get-accept-dev.offisante.fr";
    public static String PROD_URL = "https://get-accept.offisante.fr";

    public CustomApiClient() {
        super();
    }

    public CustomApiClient withErrorDecoder(ErrorDecoder errorDecoder) {
        this
                .getFeignBuilder()
                .errorDecoder(errorDecoder);
        return this;
    }

    public CustomApiClient withAddAuthorization(LoginService loginService) {
        addAuthorization("access_auth", new ApiClientRequestInterceptor(loginService));
        return this;
    }
}
