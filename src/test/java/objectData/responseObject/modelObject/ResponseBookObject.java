package objectData.responseObject.modelObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import objectData.responseObject.ResponseNotNull;
import org.testng.Assert;

@Data
public class ResponseBookObject implements ResponseNotNull {
    @Override
    public void validateNotNullFields() {
        Assert.assertNotNull(isbn);
        if (title != null) {
            Assert.assertNotNull(author);
        }
    }

    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("author")
    private String author;
    @JsonProperty("publish_date")
    private String publish_date;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("pages")
    private int pages;
    @JsonProperty("description")
    private String description;
    @JsonProperty("website")
    private String website;
}
