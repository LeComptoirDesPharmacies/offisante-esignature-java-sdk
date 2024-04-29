package fr.lecomptoirdespharmacies.offisanteESignature.client.service;

import fr.lecomptoirdespharmacies.offisanteESignature.ApiClient;
import fr.lecomptoirdespharmacies.offisanteESignature.api.DocumentsApi;
import fr.lecomptoirdespharmacies.offisanteESignature.model.*;
import fr.lecomptoirdespharmacies.offisanteESignature.client.exception.DocumentException;

public class DocumentService {
    private final ApiClient apiClient;

    public DocumentService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public String createDocument(CreateDocumentRequest documentRequest) throws DocumentException {

        DocumentsApi documentsApi = apiClient.buildClient(DocumentsApi.class);
        ApiResponse<DocumentResponse> result = documentsApi.createDocumentWithHttpInfo(documentRequest);

        if(result.getStatusCode() == 200){
            throw new DocumentException("Document already created");
        }

        if(result.getStatusCode() == 201){
            return result.getData().getDocumentUrl();
        }

        throw new DocumentException("No document returned by offisante API");
    }

    public String getDocumentStatus(String id){
        DocumentsApi documentsApi = apiClient.buildClient(DocumentsApi.class);
        return documentsApi
                .getDocumentStatus(id)
                .getStatus();
    }
}
