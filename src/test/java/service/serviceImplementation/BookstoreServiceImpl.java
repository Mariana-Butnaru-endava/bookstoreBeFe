package service.serviceImplementation;

import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import objectData.requestObject.Books;
import objectData.requestObject.RequestAccountBooks;
import objectData.requestObject.RequestAccountBook;
import service.apiService.BookstoreApiService;
import service.endpoints.BookstoreEndpoints;
import service.interfaceService.BookstoreServiceInterface;

public class BookstoreServiceImpl implements BookstoreServiceInterface {
    private final BookstoreApiService bookstoreApiService;

    public BookstoreServiceImpl() {
        bookstoreApiService = new BookstoreApiService();
    }

    @Override
    public ValidatableResponse addBooksToAccount(String token, Books body) {
        return bookstoreApiService.post(token, body, BookstoreEndpoints.BOOKSTORE_ADD);
    }

    @Override
    public ValidatableResponse updateSpecificBook(String token, RequestAccountBook body, String actualBook) {
        return bookstoreApiService.put(token, body, BookstoreEndpoints.BOOKSTORE_UPDATE + actualBook);
    }

    @Override
    public ValidatableResponse deleteBook(String token, RequestAccountBook body) {
        return bookstoreApiService.delete(token, body, BookstoreEndpoints.BOOKSTORE_DELETE_BOOK);
    }

    @Override
    public ValidatableResponse deleteBooks(String token, String userId) {
        return bookstoreApiService.delete(token, BookstoreEndpoints.BOOKSTORE_DELETE_BOOKS + userId);
    }
}
