package fr.lecomptoirdespharmacies.offisanteESignature.client.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import fr.lecomptoirdespharmacies.offisanteESignature.client.exception.AuthenticationException;

public class LoginErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        int status = response.status();

        // Invalid Login
        if (status == 401){
            return new AuthenticationException("Invalid Login: " + response.reason());
        }

        return new Default().decode(methodKey, response);
    }
}
