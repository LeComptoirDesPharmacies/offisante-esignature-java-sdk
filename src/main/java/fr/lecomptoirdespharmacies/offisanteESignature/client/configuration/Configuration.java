package fr.lecomptoirdespharmacies.offisanteESignature.client.configuration;

import fr.lecomptoirdespharmacies.offisanteESignature.model.LoginRequest;
import fr.lecomptoirdespharmacies.offisanteESignature.client.custom.CustomApiClient;

public class Configuration {

    public enum ENV {
        DEV, PROD
    }

    private final LoginRequest loginRequest;
    private final ENV environment;


    public Configuration(LoginRequest loginRequest, ENV environment) {
        this.loginRequest = loginRequest;
        this.environment = environment;
    }

    public static Configuration buildConfiguration(String userName, String password, Configuration.ENV environment){
        LoginRequest loginRequest = new LoginRequest()
                .username(userName)
                .password(password);
        return new Configuration(loginRequest, environment);
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public ENV getEnvironment() {
        return environment;
    }

    public String getBasePath(){
        return getEnvironment() == Configuration.ENV.DEV ? CustomApiClient.DEV_URL : CustomApiClient.PROD_URL;
    }
}
