package service.interfaceService;

import io.restassured.response.ValidatableResponse;
import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;

public interface BookstoreServiceInterface {
    ValidatableResponse addBooksToAccount(String token, RequestAccountBooks requestAccountBooks);
    ValidatableResponse updateSpecificBook(String token, RequestAccountBook requestBody, String actualBook);
    ValidatableResponse deleteBook(String token, RequestAccountBook requestBody);
    ValidatableResponse deleteBooks(String token, String userId);
}
