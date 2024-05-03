package fr.lecomptoirdespharmacies.offisante.esignature.client.custom;

import feign.codec.ErrorDecoder;
import fr.lecomptoirdespharmacies.offisante.esignature.ApiClient;
import fr.lecomptoirdespharmacies.offisante.esignature.client.interceptor.ApiClientRequestInterceptor;
import fr.lecomptoirdespharmacies.offisante.esignature.client.service.LoginService;

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
