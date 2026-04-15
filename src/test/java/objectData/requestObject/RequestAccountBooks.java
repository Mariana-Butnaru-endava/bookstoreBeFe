package objectData.requestObject;

import lombok.Data;
import objectData.RequestPreparation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class RequestAccountBooks implements RequestPreparation {
    private String userId;
    private List<RequestBook> collectionOfIsbns;

    public RequestAccountBooks(HashMap<String, Object> testData) {
        prepareRequestData(testData);
    }

    @Override
    public void prepareRequestData(HashMap<String, Object> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userId":
                    userId = (String) testData.get(key);
                    break;
                case "collectionOfIsbns":
                    collectionOfIsbns = getBookList(testData.get(key).toString());
                    break;
            }
        }
    }

    private List<RequestBook> getBookList(String booksData) {
        List<RequestBook> books = new ArrayList<>();
        String[] bookIds = booksData.split(",");
        for (String bookId : bookIds) {

            RequestBook book = new RequestBook();
            book.setIsbn(bookId);
            books.add(book);
            //books.add(new RequestBook().setIsbn(bookId));
        }
        return books;
    }
}
