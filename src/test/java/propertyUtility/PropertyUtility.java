package propertyUtility;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropertyUtility {
    private static Properties properties;

    public PropertyUtility(String fileName) {
        loadFile(fileName);
    }

    @SneakyThrows(Exception.class)
    private void loadFile(String fileName) {
        properties = new Properties();
        FileInputStream fis = new FileInputStream(new File("src/test/resources/RequestData/" + fileName + ".properties"));
        properties.load(fis);
    }

    public HashMap<String, Object> getProperties() {
        HashMap<String, Object> dataMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            dataMap.put(key, properties.getProperty(key));
        }
        return dataMap;
    }
}