package objectData.requestObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestBook {
    @JsonProperty("isbn")
    private String isbn;
}
