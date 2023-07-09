package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonUtil {

    public static String getValueFromJson(String file,String value) {
        try {
            return JsonPath.read(JsonPath.parse(new File(file)).jsonString(),value);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
