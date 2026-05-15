package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import objectData.requestObject.RequestBook;
import objectData.responseObject.modelObject.ResponseBookObject;
import org.testng.Assert;

import java.util.List;

@Data
public class ResponseAccBookSuccess implements ResponseNotNull {

    @JsonProperty("books")
    private List<ResponseBookObject> books;

    @Override
    public void validateNotNullFields() {
        for(ResponseBookObject book : books) {
            book.validateNotNullFields();
        }
    }

    public List<RequestBook> getIsbns() {

        return books.stream().map(book -> {
            RequestBook requestBook = new RequestBook();
            requestBook.setIsbn(book.getIsbn());
            return requestBook;
        }).toList();
    }

    public void validateBooks(List<ResponseBookObject> actualValues) {
        for (int index = 0; index < books.size(); index++) {
            Assert.assertEquals(actualValues.get(index).getIsbn(), books.get(index).getIsbn());
        }
    }
}
