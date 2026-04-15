package objectData.requestObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import objectData.RequestPreparation;

import java.util.HashMap;

@Data
public class RequestAccountBook implements RequestPreparation {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("isbn")
    private String isbn;

    public RequestAccountBook(HashMap<String, Object> testData) {
        prepareRequestData(testData);
    }

    @Override
    public void prepareRequestData(HashMap<String, Object> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userId":
                    userId = (String) testData.get(key);
                    break;
                case "isbn":
                    isbn = (String) testData.get(key);
                    break;
            }
        }
    }
}
