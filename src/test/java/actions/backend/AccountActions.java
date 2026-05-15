package actions.backend;

import com.aventstack.extentreports.Status;
import context.DataContext;
import context.keys.RequestKeys;
import context.keys.ResponseKeys;
import io.restassured.response.Response;
import objectData.requestObject.Account;
import objectData.requestObject.RequestAccount;
import objectData.responseObject.ResponseAccountFailed;
import objectData.responseObject.ResponseAccountSuccess;
import objectData.responseObject.ResponseTokenSuccess;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import reportUtility.ReportUtility;
import service.serviceImplementation.AccountServiceImpl;

public class AccountActions {
    private final AccountServiceImpl accountService;

    public AccountActions() {
        accountService = new AccountServiceImpl();
    }

    public void createAccount() {
        Account account = new Account("src/test/resources/RequestData/user.json");
        Response response = accountService
                .createAccount(account)
                .extract().response();

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);
        ResponseAccountSuccess responseAccountSuccess = response.as(ResponseAccountSuccess.class);
        Assert.assertEquals(responseAccountSuccess.getUsername(), account.getUserName());
        Assert.assertNotNull(responseAccountSuccess.getUserID());
        Assert.assertNotNull(responseAccountSuccess.getBooks());

        ReportUtility.attachReportLog(Status.PASS, "The user created a new account with success");

        DataContext.saveData(RequestKeys.REQUEST_OBJECT.getKey(), account);
        DataContext.saveData(ResponseKeys.RESPONSE_USERID.getKey(), responseAccountSuccess.getUserID());
    }

    public ResponseAccountSuccess createAccount(RequestAccount body) {
        Response response = accountService
                .createAccount(body)
                .extract().response();

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED);
        ResponseAccountSuccess responseAccountSuccess = response.as(ResponseAccountSuccess.class);
        Assert.assertEquals(responseAccountSuccess.getUsername(), body.getUserName());
        Assert.assertNotNull(responseAccountSuccess.getUserID());
        Assert.assertNotNull(responseAccountSuccess.getBooks());
        return responseAccountSuccess;
    }

    public void generateAccountToken() {
        Account account = DataContext.getData(RequestKeys.REQUEST_OBJECT.getKey(), Account.class);
        Response response = accountService
                .generateAccountToken(account)
                .extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
        ResponseTokenSuccess responseTokenSuccess = response.as(ResponseTokenSuccess.class);
        Assert.assertNotNull(responseTokenSuccess.getToken());

        ReportUtility.attachReportLog(Status.PASS, "The user generated token for account");

        DataContext.saveData(ResponseKeys.RESPONSE_TOKEN.getKey(), responseTokenSuccess.getToken());
    }

    public ResponseTokenSuccess generateAccountToken(RequestAccount body) {
        Response response = accountService
                .generateAccountToken(body)
                .extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
        ResponseTokenSuccess responseTokenSuccess = response.as(ResponseTokenSuccess.class);
        Assert.assertNotNull(responseTokenSuccess.getToken());
        return responseTokenSuccess;
    }

    public void getAccount() {
        String token = DataContext.getData(ResponseKeys.RESPONSE_TOKEN.getKey(), String.class);
        String userId = DataContext.getData(ResponseKeys.RESPONSE_USERID.getKey(), String.class);

        Account account = DataContext.getData(RequestKeys.REQUEST_OBJECT.getKey(), Account.class);
        Response response = accountService
                .getAccountById(token, userId)
                .extract().response();
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
            ResponseAccountSuccess responseAccountSuccess = response.as(ResponseAccountSuccess.class);
            Assert.assertEquals(responseAccountSuccess.getUserID(), userId);
            Assert.assertEquals(responseAccountSuccess.getUsername(), account.getUserName());

            ReportUtility.attachReportLog(Status.PASS, "Account retrieved successfully");
        } else {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_UNAUTHORIZED);
            ResponseAccountFailed responseAccountFailed = response.as(ResponseAccountFailed.class);
            Assert.assertEquals(responseAccountFailed.getCode(), "1207");
            Assert.assertEquals(responseAccountFailed.getMessage(), "User not found!");

            ReportUtility.attachReportLog(Status.PASS, "Verified account does not exist anymore");
        }
    }

    public void getAccountById(String token, String userId, Account account) {
        Response response = accountService
                .getAccountById(token, userId)
                .extract().response();
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
            ResponseAccountSuccess responseAccountSuccess = response.as(ResponseAccountSuccess.class);
            Assert.assertEquals(responseAccountSuccess.getUserID(), userId);
            Assert.assertEquals(responseAccountSuccess.getUsername(), account.getUserName());
        } else {
            Assert.assertEquals(response.statusCode(), HttpStatus.SC_UNAUTHORIZED);
            ResponseAccountFailed responseAccountFailed = response.as(ResponseAccountFailed.class);
            Assert.assertEquals(responseAccountFailed.getCode(), "1207");
            Assert.assertEquals(responseAccountFailed.getMessage(), "User not found!");
        }
    }

    public void deleteAccount() {
        String token = DataContext.getData(ResponseKeys.RESPONSE_TOKEN.getKey(), String.class);
        String userId = DataContext.getData(ResponseKeys.RESPONSE_USERID.getKey(), String.class);
        System.out.println("token in delete: " + token);
        System.out.println("userId in delete: " + userId);

        Response response = accountService
                .deleteAccount(token, userId)
                .extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NO_CONTENT);

        ReportUtility.attachReportLog(Status.PASS, "The account was deleted successfully");
    }

    public void deleteAccountById(String token, String userId) {
        Response response = accountService
                .deleteAccount(token, userId)
                .extract().response();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NO_CONTENT);
    }
}
