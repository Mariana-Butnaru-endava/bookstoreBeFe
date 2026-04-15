package objectData.requestObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ObjectFromFile {
    public void fromJsonFile(String path) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readerForUpdating(this).readValue(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
