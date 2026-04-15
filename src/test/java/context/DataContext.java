package context;

import java.util.HashMap;

public class DataContext {
    private static final HashMap<String, Object> contextData = new HashMap<>();

    public static void saveData(String key, Object value) {
        contextData.put(key, value);
    }

    public static <T> T getData(String key, Class<T> valueType) {
        if (checkKeyPresence(key)) {
            Object value = contextData.get(key);
            if (valueType.isInstance(value)) {
                return valueType.cast(value);
            } else
                throw new ClassCastException("Value for key '" + key + "' is not of type " + valueType.getName());
        } else {
            throw new IllegalArgumentException("Key not found in context: " + key);
        }
    }

    public static boolean checkKeyPresence(String key) {
        return contextData.containsKey(key);
    }

    public static void clearContext() {
        contextData.clear();
    }
}
