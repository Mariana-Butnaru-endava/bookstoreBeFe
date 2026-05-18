package actions.backend;

import com.aventstack.extentreports.Status;
import context.DataContext;
import context.keys.ResponseKeys;
import io.restassured.response.Response;
import objectData.requestObject.Books;
import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;
import objectData.responseObject.ResponseAccBookSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import reportUtility.ReportUtility;
import service.serviceImplementation.BookstoreServiceImpl;

import java.util.HashMap;

public class BookstoreActions {
    private final BookstoreServiceImpl bookstoreService;

    public BookstoreActions() {
        bookstoreService = new BookstoreServiceImpl();
    }

    public void addBooksToAccount() {
        Books books = new Books("src/test/resources/RequestData/books.json");
        books.setUserId(DataContext.getData(ResponseKeys.RESPONSE_USERID.getKey(), String.class));
        String token = DataContext.getData(ResponseKeys.RESPONSE_TOKEN.getKey(), String.class);

        Response response = bookstoreService.addBooksToAccount(token, books).extract().response();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        ResponseAccBookSuccess responseAccBookSuccess = response.as(ResponseAccBookSuccess.class);
        responseAccBookSuccess.validateNotNullFields();
        Assert.assertEquals(responseAccBookSuccess.getBooks().size(), books.getCollectionOfIsbns().size());
        Assert.assertEquals(responseAccBookSuccess.getIsbns(), books.getCollectionOfIsbns());

        ReportUtility.attachReportLog(Status.PASS, "Books successfully added to account");

        DataContext.saveData(ResponseKeys.RESPONSE_BOOKS.getKey(), responseAccBookSuccess);
    }

//    public void addBooksToAccount(String token, RequestAccountBooks requestAccountBooks) {
//        Response response = bookstoreService.addBooksToAccount(token, requestAccountBooks)
//                .extract().response();
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
//        ResponseAccBookSuccess responseAccBookSuccess = response.as(ResponseAccBookSuccess.class);
//        responseAccBookSuccess.validateNotNullFields();
//        responseAccBookSuccess.validateBooks(responseAccBookSuccess.getBooks());
//    }

    public void updateBookFromAccount(String replacementBook) {
        HashMap<String, Object> userBook = new HashMap<>();
        userBook.put("userId", DataContext.getData(ResponseKeys.RESPONSE_USERID.getKey(), String.class));
        userBook.put("isbn", replacementBook);

        String token = DataContext.getData(ResponseKeys.RESPONSE_TOKEN.getKey(), String.class);
        RequestAccountBook requestBody = new RequestAccountBook(userBook);

        ResponseAccBookSuccess accountBooks = DataContext.getData(ResponseKeys.RESPONSE_BOOKS.getKey(), ResponseAccBookSuccess.class);
        String replacedBook = accountBooks.getBooks().get(0).getIsbn();

        Response response = bookstoreService.updateSpecificBook(token, requestBody, replacedBook).extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        ResponseAccountSuccess responseSuccess = response.as(ResponseAccountSuccess.class);
        responseSuccess.validateNotNullFields();
        responseSuccess.validateBookPresence(replacementBook);

        ReportUtility.attachReportLog(Status.PASS, "Book " + replacementBook + " successfully updated in account");
    }

//    public void updateBookFromAccount(String token, RequestAccountBook requestBody, String actualBook) {
//        Response response = bookstoreService.updateSpecificBook(token, requestBody, actualBook).extract().response();
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
//        ResponseAccountSuccess responseSuccess = response.as(ResponseAccountSuccess.class);
//        responseSuccess.validateNotNullFields();
//        responseSuccess.validateBookPresence(requestBody.getIsbn());
//    }

    public void deleteBookFromAccount(String deleteBook) {
        HashMap<String, Object> userBook = new HashMap<>();
        userBook.put("userId", DataContext.getData(ResponseKeys.RESPONSE_USERID.getKey(), String.class));
        userBook.put("isbn", deleteBook);
        String token = DataContext.getData(ResponseKeys.RESPONSE_TOKEN.getKey(), String.class);
        RequestAccountBook requestBody = new RequestAccountBook(userBook);

        Response response = bookstoreService.deleteBook(token, requestBody).extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);

        ReportUtility.attachReportLog(Status.PASS, "Book " + deleteBook + " successfully deleted from account");
    }

//    public void deleteBookFromAccount(String token, RequestAccountBook requestBody) {
//        Response response = bookstoreService.deleteBook(token, requestBody).extract().response();
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
//    }

    public void deleteBooks() {
        String token = DataContext.getData(ResponseKeys.RESPONSE_TOKEN.getKey(), String.class);
        String userId = DataContext.getData(ResponseKeys.RESPONSE_USERID.getKey(), String.class);
        Response response = bookstoreService.deleteBooks(token, userId).extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);

        ReportUtility.attachReportLog(Status.PASS, "Books successfully deleted from account");
    }

//    public void deleteBooks(String token, String userId) {
//        Response response = bookstoreService.deleteBooks(token, userId).extract().response();
//        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
//    }
}
