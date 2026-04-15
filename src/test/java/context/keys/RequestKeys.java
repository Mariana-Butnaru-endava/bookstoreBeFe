package context.keys;

import lombok.Getter;

@Getter
public enum RequestKeys {
    REQUEST_OBJECT("requestObject"),
    USERNAME("userName"),
    PASSWORD("password");

    private final String key;

    RequestKeys(String key) {
        this.key = key;
    }
}
