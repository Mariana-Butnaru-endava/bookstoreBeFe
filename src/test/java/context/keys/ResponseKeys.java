package context.keys;

import lombok.Getter;

import java.awt.*;

@Getter
public enum ResponseKeys {
    RESPONSE_OBJECT("responseObject"),
    RESPONSE_USERID("userId"),
    RESPONSE_TOKEN("token"),
    RESPONSE_BOOKS("books");

    private final String key;

    ResponseKeys(String key) {
        this.key = key;
    }
}
