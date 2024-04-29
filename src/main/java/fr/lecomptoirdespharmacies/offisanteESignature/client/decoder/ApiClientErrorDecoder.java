package fr.lecomptoirdespharmacies.offisanteESignature.client.decoder;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import fr.lecomptoirdespharmacies.offisanteESignature.client.service.LoginService;

public class ApiClientErrorDecoder implements ErrorDecoder {
    private final LoginService loginService;

    public ApiClientErrorDecoder(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        int status = response.status();

        // Token seems to be expired
        if (status == 401){
            // Reset token and retry
            loginService.resetToken();

            FeignException exception = feign.FeignException.errorStatus(methodKey, response);
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request()
            );
        }

        return new ErrorDecoder.Default().decode(methodKey, response);
    }
}
