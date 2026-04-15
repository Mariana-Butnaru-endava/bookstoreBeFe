package actions.backend;

import io.restassured.response.Response;
import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;
import objectData.responseObject.ResponseAccBookSuccess;
import objectData.responseObject.ResponseAccountSuccess;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import service.serviceImplementation.BookstoreServiceImpl;

public class BookstoreActions {
    private final BookstoreServiceImpl bookstoreService;

    public BookstoreActions() {
        bookstoreService = new BookstoreServiceImpl();
    }

    public void addBooksToAccount(String token, RequestAccountBooks requestAccountBooks){
        Response response = bookstoreService.addBooksToAccount(token, requestAccountBooks)
                .extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        ResponseAccBookSuccess responseAccBookSuccess = response.as(ResponseAccBookSuccess.class);
        responseAccBookSuccess.validateNotNullFields();
        responseAccBookSuccess.validateBooks(responseAccBookSuccess.getBooks());

    }

    public void updateBookFromAccount(String token, RequestAccountBook requestBody, String actualBook){
        Response response = bookstoreService.updateSpecificBook(token, requestBody, actualBook).extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        ResponseAccountSuccess responseSuccess = response.as(ResponseAccountSuccess.class);
        responseSuccess.validateNotNullFields();
        responseSuccess.validateBookPresence(requestBody.getIsbn());
    }

    public void deleteBookFromAccount(String token, RequestAccountBook requestBody){
        Response response = bookstoreService.deleteBook(token, requestBody).extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
//        ResponseAccountSuccess responseSuccess = response.as(ResponseAccountSuccess.class);
//        responseSuccess.validateNotNullFields();
//        responseSuccess.validateBookPresence(requestBody.getIsbn());
    }

    public void deleteBooks(String token, String userId){
        Response response = bookstoreService.deleteBooks(token, userId).extract().response();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }
}
