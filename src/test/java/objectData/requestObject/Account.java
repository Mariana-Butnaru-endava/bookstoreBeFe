package objectData.requestObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import objectData.RequestPreparation;

import java.util.HashMap;
import java.util.UUID;

@Data
public class Account extends ObjectFromFile {
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;

    public Account(String dataPath) {
        fromJsonFile(dataPath);
        adjustUsername();
    }

    private void adjustUsername() {

        userName += UUID.randomUUID().toString();
    }
}
