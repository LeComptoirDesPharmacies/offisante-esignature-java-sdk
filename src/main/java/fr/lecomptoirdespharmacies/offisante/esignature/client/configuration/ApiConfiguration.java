package fr.lecomptoirdespharmacies.offisante.esignature.client.configuration;

import fr.lecomptoirdespharmacies.offisante.esignature.ApiClient;
import fr.lecomptoirdespharmacies.offisante.esignature.client.custom.CustomApiClient;
import fr.lecomptoirdespharmacies.offisante.esignature.client.decoder.ApiClientErrorDecoder;
import fr.lecomptoirdespharmacies.offisante.esignature.client.service.LoginService;
import fr.lecomptoirdespharmacies.offisante.esignature.client.decoder.LoginErrorDecoder;

public class ApiConfiguration {
    protected final ApiClient loginClient;
    protected final ApiClient apiClient;

    public ApiConfiguration(String userName, String password, Configuration.ENV environment) {
        Configuration configuration = Configuration.buildConfiguration(userName, password, environment);
        this.loginClient = initializeLoginApiClient(configuration);
        this.apiClient = initializeApiClient(configuration);
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
     *
     * @return api client
     */
    private ApiClient initializeApiClient(Configuration configuration){
        LoginService loginService = new LoginService(configuration.getLoginRequest(), loginClient);
        return new CustomApiClient()
                .withErrorDecoder(new ApiClientErrorDecoder(loginService))
                .withAddAuthorization(loginService)
                .setBasePath(configuration.getBasePath());
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
