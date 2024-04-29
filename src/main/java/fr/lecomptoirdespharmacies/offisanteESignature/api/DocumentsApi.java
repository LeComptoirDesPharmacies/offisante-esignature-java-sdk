package fr.lecomptoirdespharmacies.offisanteESignature.api;

import fr.lecomptoirdespharmacies.offisanteESignature.ApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.EncodingUtils;
import fr.lecomptoirdespharmacies.offisanteESignature.model.ApiResponse;

import fr.lecomptoirdespharmacies.offisanteESignature.model.CreateDocumentRequest;
import fr.lecomptoirdespharmacies.offisanteESignature.model.DocumentResponse;
import fr.lecomptoirdespharmacies.offisanteESignature.model.DocumentStatusResponse;
import fr.lecomptoirdespharmacies.offisanteESignature.model.InvalidTokenResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.6.0-SNAPSHOT")
public interface DocumentsApi extends ApiClient.Api {


  /**
   * Create a document for signature
   * Create a document for signature, create a salesforce registration case and return the document informations
   * @param createDocumentRequest  (required)
   * @return DocumentResponse
   */
  @RequestLine("POST /api/1/documents")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  DocumentResponse createDocument(CreateDocumentRequest createDocumentRequest);

  /**
   * Create a document for signature
   * Similar to <code>createDocument</code> but it also returns the http response headers .
   * Create a document for signature, create a salesforce registration case and return the document informations
   * @param createDocumentRequest  (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("POST /api/1/documents")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  ApiResponse<DocumentResponse> createDocumentWithHttpInfo(CreateDocumentRequest createDocumentRequest);



  /**
   * Get the status of a document
   * Get the status of a document
   * @param id Document ID (required)
   * @return DocumentStatusResponse
   */
  @RequestLine("GET /api/1/documents/{id}/status")
  @Headers({
    "Accept: application/json",
  })
  DocumentStatusResponse getDocumentStatus(@Param("id") String id);

  /**
   * Get the status of a document
   * Similar to <code>getDocumentStatus</code> but it also returns the http response headers .
   * Get the status of a document
   * @param id Document ID (required)
   * @return A ApiResponse that wraps the response boyd and the http headers.
   */
  @RequestLine("GET /api/1/documents/{id}/status")
  @Headers({
    "Accept: application/json",
  })
  ApiResponse<DocumentStatusResponse> getDocumentStatusWithHttpInfo(@Param("id") String id);


}
