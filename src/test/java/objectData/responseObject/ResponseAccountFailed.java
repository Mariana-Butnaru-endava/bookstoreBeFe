package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseAccountFailed {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
}
