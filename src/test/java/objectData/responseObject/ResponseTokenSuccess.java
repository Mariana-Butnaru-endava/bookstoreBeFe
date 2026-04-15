package objectData.responseObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseTokenSuccess {
    @JsonProperty("token")
    private String token;
    @JsonProperty("expires")
    private String expires;
    @JsonProperty("status")
    private String status;
    @JsonProperty("result")
    private String result;

}
