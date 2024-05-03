package fr.lecomptoirdespharmacies.offisante.esignature.client.service;

import fr.lecomptoirdespharmacies.offisante.esignature.ApiClient;
import fr.lecomptoirdespharmacies.offisante.esignature.api.DocumentsApi;
import fr.lecomptoirdespharmacies.offisante.esignature.client.exception.DocumentAlreadyExistsException;
import fr.lecomptoirdespharmacies.offisante.esignature.model.ApiResponse;
import fr.lecomptoirdespharmacies.offisante.esignature.model.CreateDocumentRequest;
import fr.lecomptoirdespharmacies.offisante.esignature.model.DocumentResponse;
import fr.lecomptoirdespharmacies.offisante.esignature.model.*;
import fr.lecomptoirdespharmacies.offisante.esignature.client.exception.DocumentCreationException;

public class DocumentService {
    private final ApiClient apiClient;

    public DocumentService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String createDocument(CreateDocumentRequest documentRequest) throws DocumentCreationException, DocumentAlreadyExistsException {

        DocumentsApi documentsApi = apiClient.buildClient(DocumentsApi.class);
        ApiResponse<DocumentResponse> result = documentsApi.createDocumentWithHttpInfo(documentRequest);

        if(result.getStatusCode() == 200){
            throw new DocumentAlreadyExistsException("Document already created");
        }

        if(result.getStatusCode() == 201){
            return result.getData().getDocumentUrl();
        }

        throw new DocumentCreationException("No document returned by offisante API");
    }

    public String getDocumentStatus(String id){
        DocumentsApi documentsApi = apiClient.buildClient(DocumentsApi.class);
        return documentsApi
                .getDocumentStatus(id)
                .getStatus();
    }
}
