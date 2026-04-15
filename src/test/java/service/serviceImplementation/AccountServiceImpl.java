package service.serviceImplementation;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import objectData.requestObject.RequestAccount;
import service.apiService.AccountApiService;
import service.endpoints.AccountEndpoints;
import service.interfaceService.AccountServiceInterface;

public class AccountServiceImpl implements AccountServiceInterface {
    private final AccountApiService accountApiService;

    public AccountServiceImpl() {
        accountApiService = new AccountApiService();
    }

    @Override
    public ValidatableResponse createAccount(Object body) {
        return accountApiService.post(body, AccountEndpoints.ACCOUNT_CREATE);
    }

    @Override
    public ValidatableResponse generateAccountToken(Object body) {
        return accountApiService.post(body, AccountEndpoints.ACCOUNT_TOKEN);
    }

    @Override
    public ValidatableResponse getAccountById(String token, String userId) {
        return accountApiService.get(token, AccountEndpoints.ACCOUNT_GET + userId);
    }

    @Override
    public ValidatableResponse deleteAccount(String token, String userId) {
        return accountApiService.delete(token, AccountEndpoints.ACCOUNT_DELETE + userId);
    }
}
