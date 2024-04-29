package fr.lecomptoirdespharmacies.offisanteESignature.client.decoder;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import fr.lecomptoirdespharmacies.offisanteESignature.client.repository.TokenRepository;

public class ApiClientErrorDecoder implements ErrorDecoder {

    private final TokenRepository tokenRepository;

    public ApiClientErrorDecoder(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        int status = response.status();

        // Token seems to be expired
        if (status == 401){
            // Reset token and retry
            FeignException exception = feign.FeignException.errorStatus(methodKey, response);
            tokenRepository.save(null);
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
