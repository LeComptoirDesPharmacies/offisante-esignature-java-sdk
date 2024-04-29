package fr.lecomptoirdespharmacies.offisanteESignature.client.configuration;

import fr.lecomptoirdespharmacies.offisanteESignature.ApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.client.custom.CustomApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.client.decoder.ApiClientErrorDecoder;
import fr.lecomptoirdespharmacies.offisanteESignature.client.service.LoginService;
import fr.lecomptoirdespharmacies.offisanteESignature.client.repository.TokenRepository;
import fr.lecomptoirdespharmacies.offisanteESignature.client.decoder.LoginErrorDecoder;

public class ApiConfiguration {
    protected final ApiClient loginClient;
    protected final ApiClient apiClient;

    public ApiConfiguration(String userName, String password, Configuration.ENV environment) {
        Configuration configuration = Configuration.buildConfiguration(userName, password, environment);
        this.loginClient = initializeLoginApiClient(configuration);
        this.apiClient = initializeApiClient(configuration, this.loginClient, new TokenRepository());
    }

    /**
     * Initialize login api client with error decoder
     *
     * @param configuration configuration
     *
     * @return login api client
     */
    private ApiClient initializeLoginApiClient(Configuration configuration){
        return new CustomApiClient()
                .withErrorDecoder(new LoginErrorDecoder())
                .setBasePath(configuration.getBasePath());
    }

    /**
     * Initialize api client with error decoder and authorization interceptor
     *
     * @param configuration configuration
     * @param loginClient login client
     * @param tokenRepository token repository
     *
     * @return api client
     */
    private ApiClient initializeApiClient(Configuration configuration, ApiClient loginClient, TokenRepository tokenRepository){
        LoginService loginService = new LoginService(configuration.getLoginRequest(), loginClient, tokenRepository);
        return new CustomApiClient()
                .withErrorDecoder(new ApiClientErrorDecoder(tokenRepository))
                .withAddAuthorization(tokenRepository, loginService)
                .setBasePath(configuration.getBasePath());
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
