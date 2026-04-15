package service.interfaceService;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import objectData.requestObject.RequestAccount;

public interface AccountServiceInterface {
    ValidatableResponse createAccount(Object body);
    ValidatableResponse generateAccountToken(Object body);
    ValidatableResponse getAccountById(String token, String userId);
    ValidatableResponse deleteAccount(String token, String userId);
}
