package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import objectData.responseObject.modelObject.ResponseBookObject;
import org.testng.Assert;

import java.util.List;

@Data
public class ResponseAccountSuccess implements ResponseNotNull {
    @JsonProperty("userID")
    @JsonAlias("userId")
    private String userID;
    @JsonProperty("username")
    private String username;
    @JsonProperty("books")
    private List<ResponseBookObject> books;

    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(userID);
        Assert.assertNotNull(username);
        Assert.assertNotNull(books);
        for (ResponseBookObject book : books) {
            book.validateNotNullFields();
        }
    }

    public void validateBookPresence(String actualBook) {
        Assert.assertTrue(books.stream().anyMatch(book -> book.getIsbn().equals(actualBook)),
                "Book with ISBN " + actualBook + " is not present in the account.");
    }
}
