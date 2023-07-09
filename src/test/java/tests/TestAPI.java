package tests;

import api_utils.JsonUtils;
import consts.Endpoints;
import consts.TestData;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import models.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.Step;
import utils.ApiUtil;
import java.util.List;


@Slf4j
public class TestAPI {
    TestDataModel testData = JsonUtils.createModelFromJSON(TestData.TEST_DATA_PATH.label, TestDataModel.class);
    PostModel newPostForValidUser = JsonUtils.createModelFromJSON(TestData.TEST_VALID_POST_FOR_VALID_USER_PATH.label, PostModel.class);
    UserModel testValidUser = JsonUtils.createModelFromJSON(TestData.TEST_VALID_USER_PATH.label,UserModel.class);

    @Test
    public void GetGoodPostsWithAscendingOrder() {
        log.info("Получение всех постов и их сортировка");
        List<PostModel> allPosts = ApiUtil.getAllResults(Endpoints.GET_ALL_POSTS,HttpStatus.SC_OK,ContentType.JSON, PostModel.class);
        Assert.assertTrue(Step.isAscendingOrder(Step.getListId(allPosts)), "Сообщения сортируются не по возрастанию");
    }

    @Test
    public void GetGoodPostTitleAndBodyIsNotEmpty() {
        log.info("Получение валидного поста");
        PostModel post99 = ApiUtil.getById(Endpoints.GET_POST_BY_ID, testData.getValidPostId(), HttpStatus.SC_OK, PostModel.class);
        Assert.assertEquals(post99.getId(),testData.getValidPostId(),"id не равен" + testData.getValidPostId());
        Assert.assertEquals(post99.getUserId(),testData.getValidUserIdForValidPost(),"UserId не равен" + testData.getValidUserIdForValidPost());
        Assert.assertFalse(post99.getTitle().isEmpty(), "Заголовок поста пустой");
        Assert.assertFalse(post99.getBody().isEmpty(), "Тело поста пустое");
    }

    @Test
    public void GetBadPostRequestBodyIsEmpty() {
        log.info("Получение не валидного поста");
        ApiUtil.getById(Endpoints.GET_POST_BY_ID, testData.getInvalidPostId(), HttpStatus.SC_NOT_FOUND,PostModel.class);
        Assert.assertTrue(JsonUtils.isJsonBodyEmpty(ApiUtil.getResponseById(Endpoints.GET_POST_BY_ID, testData.getInvalidPostId())),"Тело ответа не пустое");
    }

    @Test
    public void PostGoodPostIdExist() {
        log.info("Отправка валидного поста для валидного пользователя");
        PostModel resultPost = ApiUtil.postClassModel(newPostForValidUser,ContentType.JSON,Endpoints.POST_POST,HttpStatus.SC_CREATED,PostModel.class);
        Assert.assertEquals(newPostForValidUser.getTitle(),resultPost.getTitle(),"Отправленный заголовок не равен полученному");
        Assert.assertEquals(newPostForValidUser.getBody(),resultPost.getBody(),"Отправленное тело не равно полученному");
        Assert.assertEquals(newPostForValidUser.getUserId(),resultPost.getUserId(),"Отправленный userId не равен полученному");
        Assert.assertNotNull(resultPost.getId(),"id не присутствует в ответе");
    }

    @Test
    public void GetGoodUsersSelectionAndVerification() {
        log.info("Получение всех пользователей");
        List<UserModel> allUsers = ApiUtil.getAllResults(Endpoints.GET_ALL_USERS,HttpStatus.SC_OK,ContentType.JSON,UserModel.class);
        Assert.assertEquals(Step.getUserById(allUsers,testData.getValidUserId()), testValidUser, "Полученный пользователь не равен тестовому");
    }

    @Test
    public void GetGoodUserVerification() {
        log.info("Получение валидного пользователя");
        UserModel user5 = ApiUtil.getById(Endpoints.GET_USER_BY_ID,testData.getValidUserId(),HttpStatus.SC_OK,UserModel.class);
        Assert.assertEquals(user5, testValidUser, "Полученный пользователь не равен тестовому");
    }
}

