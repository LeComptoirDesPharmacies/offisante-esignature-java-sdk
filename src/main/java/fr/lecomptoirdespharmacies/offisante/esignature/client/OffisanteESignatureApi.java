package fr.lecomptoirdespharmacies.offisante.esignature.client;

import fr.lecomptoirdespharmacies.offisante.esignature.client.configuration.ApiConfiguration;
import fr.lecomptoirdespharmacies.offisante.esignature.client.configuration.Configuration;
import fr.lecomptoirdespharmacies.offisante.esignature.client.service.DocumentService;

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
