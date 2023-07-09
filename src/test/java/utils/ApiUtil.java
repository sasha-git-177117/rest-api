package utils;

import api_utils.ApiUtils;
import consts.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import java.util.List;

public class ApiUtil {

    public static String getURL(String baseUrl, Endpoints endpoint) {
        return baseUrl + endpoint.label;
    }

    public static <T> T getById(Endpoints endpoint, int id, int status, Class<T> type) {
        Response response = getResponseById(endpoint,id);
        Assert.assertEquals(response.statusCode(),status,"Код состояния не равен " + status);
        return  response
                .then()
                .extract()
                .body()
                .as(type);
    }

    public static <T> List<T> getAllResults(Endpoints endpoint, int status, ContentType contentType, Class<T> type) {
        Response response = getResponse(endpoint);
        Assert.assertEquals(response.statusCode(),status,"Код состояния не равен " + status);
        Assert.assertTrue(response.contentType().contains(contentType.toString()),"Тело ответа не равно " + contentType);
        return response
                .then()
                .extract()
                .jsonPath()
                .getList("",type);
    }

    public static  <T> T postClassModel(Object modelClass, ContentType contentType, Endpoints endpoint, int status, Class<T> type) {
        Response response = ApiUtils.postClassModel(modelClass,contentType,getURL(Configuration.getURL(),endpoint));
        Assert.assertEquals(response.statusCode(),status,"Код состояния не равен " + status);
        return response
                .then()
                .assertThat()
                .statusCode(status)
                .extract()
                .as(type);
    }

    public static Response getResponseById(Endpoints endpoint, int id) {
        return ApiUtils.getResponse(String.format(getURL(Configuration.getURL(),endpoint), id));
    }

    public static Response getResponse(Endpoints endpoint) {
        return ApiUtils.getResponse(getURL(Configuration.getURL(),endpoint));
    }
}
