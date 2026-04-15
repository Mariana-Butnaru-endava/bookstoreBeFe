package restClient;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public enum RestRequestType {
    GET() {
        @Override
        public Response performRequest(RequestSpecification requestSpec, String endpoint) {
            RequestSpecification preparedSpec = prepareClient(requestSpec);
            return preparedSpec
                    .log().all()
                    .when()
                    .get(endpoint)
                    .then()
                    .log().all()
                    .extract().response();
        }
    },
    POST() {
        @Override
        public Response performRequest(RequestSpecification requestSpec, String endpoint) {
            RequestSpecification preparedSpec = prepareClient(requestSpec);
            return preparedSpec
                    .log().all()
                    .when()
                    .post(endpoint)
                    .then()
                    .log().all()
                    .extract().response();
        }
    },
    PUT() {
        @Override
        public Response performRequest(RequestSpecification requestSpec, String endpoint) {
            RequestSpecification preparedSpec = prepareClient(requestSpec);
            return preparedSpec
                    .log().all()
                    .when()
                    .put(endpoint)
                    .then()
                    .log().all()
                    .extract().response();
        }
    },
    DELETE() {
        @Override
        public Response performRequest(RequestSpecification requestSpec, String endpoint) {
            RequestSpecification preparedSpec = prepareClient(requestSpec);
            return preparedSpec
                    .log().all()
                    .when()
                    .delete(endpoint)
                    .then()
                    .log().all()
                    .extract().response();
        }
    };

    private static RequestSpecification prepareClient(RequestSpecification requestSpec) {
        requestSpec.baseUri("https://demoqa.com");
        requestSpec.contentType("application/json");
        return requestSpec;
    }

    public abstract Response performRequest(RequestSpecification requestSpec, String endpoint);
}
