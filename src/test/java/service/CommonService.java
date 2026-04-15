package service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import restClient.RequestType;
import restClient.RestClient;
import restClient.RestRequestType;

public class CommonService {
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";
    //layer 2
//    public ValidatableResponse post(Object body, String endpoint) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .setBody(body).build();
//        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.POST);
//        Response response = performRequest(RequestType.POST, requestSpecification, endpoint).extract().response();
//        ServiceHelper.responseLogs(response);
//        return response.then();
//    }

//    public ValidatableResponse post(String token, Object body, String endpoint) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .addHeader(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
//                .setBody(body)
//                .build();
//        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.POST);
//        Response response = performRequest(RequestType.POST, requestSpecification, endpoint).extract().response();
//        ServiceHelper.responseLogs(response);
//        return response.then();
//    }

//    public ValidatableResponse get(String token, String endpoint) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .addHeader(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
//                .build();
//        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.GET);
//        Response response = performRequest(RequestType.GET, requestSpecification, endpoint).extract().response();
//        ServiceHelper.responseLogs(response);
//        return response.then();
//    }

//    public ValidatableResponse put(String token, Object body, String endpoint) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .addHeader(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
//                .setBody(body)
//                .build();
//        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.PUT);
//        Response response = performRequest(RequestType.PUT, requestSpecification, endpoint).extract().response();
//        ServiceHelper.responseLogs(response);
//        return response.then();
//    }

//    public ValidatableResponse delete(String token, String endpoint) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .addHeader(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token).build();
//        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.DELETE);
//        Response response = performRequest(RequestType.DELETE, requestSpecification, endpoint).extract().response();
//        ServiceHelper.responseLogs(response);
//        return response.then();
//    }

//    public ValidatableResponse delete(String token, Object body, String endpoint) {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .addHeader(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
//                .setBody(body)
//                .build();
//        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.DELETE);
//        Response response = performRequest(RequestType.DELETE, requestSpecification, endpoint).extract().response();
//        ServiceHelper.responseLogs(response);
//        return response.then();
//    }


    public ValidatableResponse post(Object body, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.body(body);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.POST);

        Response response = performRequest(RestRequestType.POST, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response.then();
    }

    public ValidatableResponse post(String token, Object body, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given()
                .header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
                .body(body);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.POST);

        Response response = performRequest(RestRequestType.POST, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response.then();
    }

    public ValidatableResponse get(String token, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given()
                .header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.GET);

        Response response = performRequest(RestRequestType.GET, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response.then();
    }

    public ValidatableResponse put(String token, Object body, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given()
                .header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
                .body(body);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.PUT);

        Response response = performRequest(RestRequestType.PUT, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response.then();
    }

    public ValidatableResponse delete(String token, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given()
                .header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.DELETE);

        Response response = performRequest(RestRequestType.DELETE, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response.then();
    }

    public ValidatableResponse delete(String token, Object body, String endpoint) {
        RequestSpecification requestSpecification = RestAssured.given()
                .header(AUTHORIZATION_HEADER_KEY, AUTHORIZATION_TYPE + token)
                .body(body);
        ServiceHelper.requestLogs(requestSpecification, endpoint, RequestType.DELETE);

        Response response = performRequest(RestRequestType.DELETE, requestSpecification, endpoint);
        ServiceHelper.responseLogs(response);
        return response.then();
    }

    //    private ValidatableResponse performRequest(String requestType, RequestSpecification reqSpec, String endpoint) {
//        return new RestClient().performRequest(requestType, reqSpec, endpoint);
//    }
    private Response performRequest(RestRequestType reqType, RequestSpecification reqSpec, String endpoint) {
        return reqType.performRequest(reqSpec, endpoint);
    }
}
