package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import loggerUtility.LoggerUtility;
import lombok.SneakyThrows;

import xmlFiles.GeneralXML;
import xmlFiles.xmlNode.Configuration;

public class ServiceHelper {

    public static void requestLogs(RequestSpecification requestSpecification, String path, String method) {
        LoggerUtility.logInfo("====== Info about request: ======");
        LoggerUtility.logInfo("====== Request method: " + method);
        LoggerUtility.logInfo("====== " + getPath(path));
        LoggerUtility.logInfo("====== Request body: " + getRequestBody(requestSpecification));
    }

    public static void responseLogs(Response response) {
        LoggerUtility.logInfo("====== Info about response: ======");
        LoggerUtility.logInfo("====== Response time: " + response.getTime());
        LoggerUtility.logInfo("====== Response status: " + response.getStatusCode());
        LoggerUtility.logInfo("====== Response status line: " + response.getStatusLine());
        LoggerUtility.logInfo("====== Response body: " + getResponseBody(response));
    }

    public static String getPath(String path) {
        Configuration configuration = GeneralXML.createConfig(Configuration.class);
        return "Request URI: " + configuration.getBackEndConfig().getBaseURL() + path;
    }

    @SneakyThrows(Exception.class)
    public static String getRequestBody(RequestSpecification requestSpecification) {
        String json;
        Object objBody = ((RequestSpecificationImpl) requestSpecification).getBody();
        if (objBody != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.readTree(objBody.toString()).toPrettyString();
        } else {
            json = "No body in request";
        }
        return json;
    }

    public static String getResponseBody(Response response) {
        String responseBody = "";
        if (response.getBody() != null) {
            responseBody = response.getBody().prettyPrint();
        } else {
            responseBody = "No body in response";
        }
        return responseBody;
    }
}
