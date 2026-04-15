package objectData.requestObject;

import lombok.Data;
import objectData.RequestPreparation;

import java.util.HashMap;
import java.util.UUID;

@Data
public class RequestAccount implements RequestPreparation {
    private String userName;
    private String password;

    public RequestAccount(HashMap<String, Object> testData) {
        prepareRequestData(testData);
    }

    @Override
    public void prepareRequestData(HashMap<String, Object> testData) {
        for (String key : testData.keySet()) {
            switch (key) {
                case "userName":
                    userName = (String) testData.get(key);
                    break;
                case "password":
                    password = (String) testData.get(key);
                    break;
            }
        }
        adjustUsername();
    }

    private void adjustUsername() {
        userName = userName + UUID.randomUUID().toString();
    }
}
