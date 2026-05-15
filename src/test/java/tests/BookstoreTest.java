//package tests;
//
//import actions.backend.AccountActions;
//import actions.backend.BookstoreActions;
//import com.aventstack.extentreports.Status;
//import hooks.Hooks;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.response.ValidatableResponse;
//import io.restassured.specification.RequestSpecification;
//import objectData.requestObject.RequestAccount;
//import objectData.requestObject.RequestAccountBooks;
//import objectData.requestObject.RequestAccountBook;
//import objectData.responseObject.ResponseAccountSuccess;
//import objectData.responseObject.ResponseTokenSuccess;
//import org.apache.http.HttpStatus;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import propertyUtility.PropertyUtility;
//import reportUtility.ReportUtility;
//
//import java.util.HashMap;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.notNullValue;
//
//public class BookstoreTest extends Hooks {
//    String userId;
//    String token;
//    RequestAccount requestAccount;
//    AccountActions accountActions;
//    BookstoreActions bookstoreActions;
//
//    @Test
//    public void addBooksToAccountTest() {
//        System.out.println("Step 1: Create new account");
//        createAccountF();
//        ReportUtility.attachReportLog(Status.PASS, "The user created a new account with success");
//
//        System.out.println("Step 2: Generate new token");
//        generateTokenF();
//        ReportUtility.attachReportLog(Status.PASS, "The user generated token for account");
//
//        System.out.println("Step 3: Get new account");
//        getSpecificAccountF();
//        ReportUtility.attachReportLog(Status.PASS, "Account retrieved successfully");
//
//        System.out.println("Step 4: Add books to account");
//        addBooksToAccount();
//        ReportUtility.attachReportLog(Status.PASS, "The books added to account successfully");
//
//        System.out.println("Step 5: Get account with books");
//        getSpecificAccountF();
//        ReportUtility.attachReportLog(Status.PASS, "The books added are visible in account");
//
//        System.out.println("Step 6: Update book from list of books");
//        updateBook();
//        ReportUtility.attachReportLog(Status.PASS, "The books updated to account successfully");
//
//        System.out.println("Step 7: Delete book from list of books");
//        deleteBook();
//        ReportUtility.attachReportLog(Status.PASS, "The books deleted from account");
//
//        System.out.println("Step 8: Get account with books");
//        getSpecificAccountF();
//        ReportUtility.attachReportLog(Status.PASS, "The account does not contain book");
//
//        System.out.println("Step 9: Delete all books from the account");
//        deleteBooks();
//        ReportUtility.attachReportLog(Status.PASS, "The books from account were deleted successfully");
//
//        System.out.println("Step 10: Get account with books");
//        getSpecificAccountF();
//        ReportUtility.attachReportLog(Status.PASS, "The account does not contain books");
//
//        System.out.println("Step 11: Delete account");
//        deleteSpecificAccount();
//        ReportUtility.attachReportLog(Status.PASS,"The account was deleted successfully");
//
//        System.out.println("Step 12: Verify account deleted successfully");
//        getSpecificAccountF();
//        ReportUtility.attachReportLog(Status.PASS,"Verified account does not exist anymore");
//
//    }
//
//    private void deleteBooks() {
//        bookstoreActions.deleteBooks(token, userId);
//    }
//
//    private void deleteBook() {
//        propertyUtility = new PropertyUtility("BooksData");
//        HashMap<String, Object> books = new HashMap<>();
//        String expectedBook = propertyUtility.getProperties().get("expectedBook").toString();
//        books.put("userId", userId);
//        books.put("isbn", expectedBook);
//        RequestAccountBook requestAccountBook = new RequestAccountBook(books);
//        bookstoreActions.deleteBookFromAccount(token, requestAccountBook);
//    }
//
//    private void updateBook() {
//        propertyUtility = new PropertyUtility("BooksData");
//        HashMap<String, Object> books = propertyUtility.getProperties();
//        String actualBook = books.get("actualBook").toString();
//        String expectedBook = books.get("expectedBook").toString();
//        books.put("userId", userId);
//        books.put("isbn", expectedBook);
//        RequestAccountBook requestAccountBook = new RequestAccountBook(books);
//        bookstoreActions.updateBookFromAccount(token, requestAccountBook, actualBook);
//    }
//
//    private void addBooksToAccount() {
//        bookstoreActions = new BookstoreActions();
//        propertyUtility = new PropertyUtility("BooksData");
//        HashMap<String, Object> books = propertyUtility.getProperties();
//        books.put("userId", userId);
//        RequestAccountBooks requestAccountBooks = new RequestAccountBooks(books);
////        requestBookstore.setUserId(userId);
//        bookstoreActions.addBooksToAccount(token, requestAccountBooks);
//    }
//
//    private void createAccountF() {
//        accountActions = new AccountActions();
//        propertyUtility = new PropertyUtility("CreateAccountData");
//        requestAccount = new RequestAccount(propertyUtility.getProperties());
//        ResponseAccountSuccess responseAccountSuccess = accountActions.createAccount(requestAccount);
//        userId = responseAccountSuccess.getUserID();
//    }
//
//    public void createAccount() {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .setBaseUri("https://demoqa.com/")
//                .setContentType("application/json")
//                .build();
//        propertyUtility = new PropertyUtility("CreateAccountData");
//        requestAccount = new RequestAccount(propertyUtility.getProperties());
////        requestAccount.setUserName("TesT" + UUID.randomUUID().toString());
////        requestAccount.setPassword("Test@123456");
//
////        JSONObject requestBody = new JSONObject();
////        requestBody.put("userName", "TesT" + UUID.randomUUID());
////        requestBody.put("password", "Test@123456");
//        requestSpecification.body(requestAccount);
//        ValidatableResponse response = given()
//                .spec(requestSpecification)
//                .log().all()
//                .when()
//                .post("Account/v1/User")
//                .then()
//                .log().all()
//                .statusCode(HttpStatus.SC_CREATED)
//                .body("username", equalTo(requestAccount.getUserName()))
//                .body("userID", notNullValue())
//                .body("books", notNullValue());
//        System.out.println("Response status code: " + response.extract().statusCode());
//        System.out.println("Response status line: " + response.extract().statusLine());
//        System.out.println("Response body userID: " + response.extract().body().jsonPath().get("userID"));
//        ResponseAccountSuccess responseAccountBody = response.extract().body().as(ResponseAccountSuccess.class);
//        userId = responseAccountBody.getUserID();
//        System.out.println("Response body userID: " + userId);
//        System.out.printf("Response body username: %s%n", responseAccountBody.getUsername());
//        System.out.printf("Response body books: %s%n", responseAccountBody.getBooks());
//        Assert.assertEquals(responseAccountBody.getUsername(), requestAccount.getUserName());
//        Assert.assertNotNull(userId);
//        Assert.assertNotNull(responseAccountBody.getBooks());
//    }
//
//    private void generateTokenF() {
//        ResponseTokenSuccess responseTokenSuccess = accountActions.generateAccountToken(requestAccount);
//        token = responseTokenSuccess.getToken();
//    }
//
//    public void generateToken() {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .setBaseUri("https://demoqa.com/")
//                .setContentType("application/json")
//                .build();
//
//        requestSpecification.body(requestAccount);
//        ValidatableResponse response = given()
//                .spec(requestSpecification)
//                .log().all()
//                .when()
//                .post("Account/v1/GenerateToken")
//                .then()
//                .log().all()
//                .statusCode(HttpStatus.SC_OK)
//                .body("token", notNullValue());
////                .body("username", equalTo(requestAccount.getUserName()))
////                .body("userID", notNullValue())
////                .body("books", notNullValue());
//        System.out.println("Response status code: " + response.extract().statusCode());
//        System.out.println("Response status line: " + response.extract().statusLine());
//        System.out.println("Response body token: " + response.extract().body().jsonPath().get("token"));
//        ResponseTokenSuccess responsetoken = response.extract().body().as(ResponseTokenSuccess.class);
//        token = responsetoken.getToken();
//        System.out.println("Response body token: " + token);
//
//        Assert.assertNotNull(token);
//        Assert.assertEquals(responsetoken.getStatus(), "Success");
//        Assert.assertEquals(responsetoken.getResult(), "User authorized successfully.");
//    }
//
//    private void getSpecificAccountF() {
//        accountActions.getAccountById(token, userId, requestAccount);
//    }
//
//    private void getSpecificAccount() {
//        RequestSpecification requestSpecification = new RequestSpecBuilder()
//                .setBaseUri("https://demoqa.com/")
//                .setContentType("application/json")
//                .addHeader("Authorization", "Bearer " + token)
//                .build();
//
//        ValidatableResponse response = given()
//                .spec(requestSpecification)
//                .log().all()
//                .when()
//                .get("Account/v1/User/" + userId)
//                .then()
//                .log().all()
//                .statusCode(HttpStatus.SC_OK)
//                .body("username", equalTo(requestAccount.getUserName()))
//                .body("userId", equalTo(userId))
//                .body("books", notNullValue());
//        System.out.println("Response status code: " + response.extract().statusCode());
//        System.out.println("Response status line: " + response.extract().statusLine());
//        System.out.println("Response body userID: " + response.extract().body().jsonPath().get("userId"));
//        ResponseAccountSuccess responseAccount = response.extract().body().as(ResponseAccountSuccess.class);
//        Assert.assertNotNull(responseAccount.getUserID());
//        Assert.assertEquals(responseAccount.getUsername(), requestAccount.getUserName());
//        Assert.assertEquals(responseAccount.getUserID(), userId);
//    }
//
//    private void deleteSpecificAccount() {
//        accountActions.deleteAccountById(token, userId);
//    }
//
//}
