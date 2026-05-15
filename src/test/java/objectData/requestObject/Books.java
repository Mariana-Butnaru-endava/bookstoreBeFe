package objectData.requestObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Books extends ObjectFromFile {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("collectionOfIsbns")
    private List<RequestBook> collectionOfIsbns;

    public Books(String dataPath) {
        fromJsonFile(dataPath);
    }
 }
