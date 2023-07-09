package api_utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.MarkerFactory;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonUtils {

    public static boolean isJsonBodyEmpty(Response response) {
        JSONObject JsonObj = new JSONObject(response
                .then()
                .extract()
                .asString());
        return JsonObj.isEmpty();
    }

    public static  <T> T createModelFromJSON(String file, Class<T> modelClass) {
        try {
            return new ObjectMapper().getFactory().createParser(new File(file)).readValueAs(modelClass);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
