package fr.lecomptoirdespharmacies.offisanteESignature.api;

import fr.lecomptoirdespharmacies.offisanteESignature.ApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.EncodingUtils;
import fr.lecomptoirdespharmacies.offisanteESignature.model.ApiResponse;

import fr.lecomptoirdespharmacies.offisanteESignature.model.InvalidTokenResponse;
import fr.lecomptoirdespharmacies.offisanteESignature.model.LoginRequest;
import fr.lecomptoirdespharmacies.offisanteESignature.model.ValidTokenResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.6.0-SNAPSHOT")
public interface AuthenticationApi extends ApiClient.Api {


  /**
   * API Authentication
   * Authenticate to the API
   * @param loginRequest  (required)
   * @return ValidTokenResponse
   */
  @RequestLine("POST /api/auth")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  ValidTokenResponse login(LoginRequest loginRequest);

  /**
   * API Authentication
   * Similar to <code>login</code> but it also returns the http response headers .
   * Authenticate to the API
   * @param loginRequest  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/auth")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  ApiResponse<ValidTokenResponse> loginWithHttpInfo(LoginRequest loginRequest);


}
