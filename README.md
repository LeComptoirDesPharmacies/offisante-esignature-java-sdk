# offisante-esignature-java-sdk

This SDK allows you to easily integrate the Offisante eSignature API into your Java application.

## Installation

### Maven

Add the following dependency to your `pom.xml` file:

```xml
    <dependency>
        <groupId>fr.lecomptoirdespharmacies</groupId>
        <artifactId>offisante-esignature-java-sdk</artifactId>
        <version>0.1.0</version>
        <scope>compile</scope>
    </dependency>

```

### SBT

Add the following dependency to your `build.sbt` file:

```scala
libraryDependencies += "fr.lecomptoirdespharmacies" % "offisante-esignature-java-sdk" % "0.1.0"
```

### Gradle

Add the following dependency to your `build.gradle` file:

```groovy
implementation 'fr.lecomptoirdespharmacies:offisante-esignature-java-sdk:0.1.0'
```

## Usage

### Configuration

You need to configure the SDK with your Offisante eSignature API credentials. You can do this by creating a `OffisanteESignatureApi` object:

```java
String userName    = "xxx";
String password    = "yyy";
String environment = Configuration.ENV.DEV;
OffisanteESignatureApi offisanteEsignatureApi = new OffisanteESignatureApi(userName, password, environment);
```

### Create a document request

You can create a document request by calling the `createDocument` method:

```java

try{
    CreateDocumentRequest documentRequest = new CreateDocumentRequest()
                .email("email@mail.com")
                .firstName("first")
                .lastName("last")
                .phone("0568999745");

    String documentURL = offisanteEsignatureApi
                            .getDocumentService()
                            .createDocument(
                                documentRequest
                            );
}catch(DocumentAlreadyExistsException e){
    // Handle exception
}catch(DocumentCreationException e){
    // Handle exception
}
```

### Get a document status

You can get a document status by calling the `getDocumentStatus` method:

```java
try{
    DocumentStatus documentStatus = offisanteEsignatureApi
                                        .getDocumentService()
                                        .getDocumentStatus(documentId);
}catch(DocumentNotFoundException e){
    // Handle exception
}
```


