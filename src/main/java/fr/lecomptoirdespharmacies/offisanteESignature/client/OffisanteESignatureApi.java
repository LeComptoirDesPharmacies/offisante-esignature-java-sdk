package fr.lecomptoirdespharmacies.offisanteESignature.client;

import fr.lecomptoirdespharmacies.offisanteESignature.client.configuration.ApiConfiguration;
import fr.lecomptoirdespharmacies.offisanteESignature.client.configuration.Configuration;
import fr.lecomptoirdespharmacies.offisanteESignature.client.service.DocumentService;

public class OffisanteESignatureApi {
    private final DocumentService documentService;

    public OffisanteESignatureApi(String userName, String password, Configuration.ENV environment) {
        ApiConfiguration apiConfiguration = new ApiConfiguration(userName, password, environment);
        this.documentService = new DocumentService(apiConfiguration.getApiClient());
    }

    public DocumentService getDocumentService() {
        return documentService;
    }
}
