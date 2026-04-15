package restClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import xmlFiles.GeneralXML;
import xmlFiles.xmlNode.Configuration;

import static io.restassured.RestAssured.given;

public class RestClient {
    //layer 1
    private RequestSpecification prepareRequest(RequestSpecification requestSpec) {
        Configuration configuration = GeneralXML.createConfig(Configuration.class);

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addRequestSpecification(requestSpec)
                .setBaseUri(configuration.getBackEndConfig().getBaseURL())
                .setContentType(configuration.getBackEndConfig().getContentType())
                .build();
        return requestSpecification;
    }

    public ValidatableResponse performRequest(String method, RequestSpecification reqSpec, String endpoint) {
        RequestSpecification requestSpecification = prepareRequest(reqSpec);
        ValidatableResponse response = null;
        switch (method.toUpperCase()) {
            case RequestType.POST:
                response = given()
                        .spec(requestSpecification)
                        .log().all()
                .when()
                        .post(endpoint)
                .then()
                        .log().all();
                break;
            case RequestType.GET:
                response = given()
                        .spec(requestSpecification)
                        .log().all()
                .when()
                        .get(endpoint)
                .then()
                        .log().all();
                break;
            case RequestType.PUT:
                response = given()
                        .spec(requestSpecification)
                        .log().all()
                        .when()
                        .put(endpoint)
                        .then()
                        .log().all();
                break;
            case RequestType.DELETE:
                response = given()
                        .spec(requestSpecification)
                        .log().all()
                .when()
                        .delete(endpoint)
                        .then()
                        .log().all();
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
        return response;
    }
}
