package com.vinted.api.automation.postmanagement;

import com.google.gson.Gson;
import com.vinted.api.automation.http.HttpOperationImpl;
import com.vinted.api.automation.http.HttpOperations;
import com.vinted.api.automation.model.common.User;
import com.vinted.api.automation.model.request.post.CreatePostRequest;
import com.vinted.api.automation.model.response.ErrorResponse;
import com.vinted.api.automation.model.response.ServiceResponse;
import com.vinted.api.automation.model.response.post.PostResponse;
import com.vinted.api.automation.model.response.user.UserResponse;
import com.vinted.api.automation.usermanagment.CreateUserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.vinted.api.automation.URLManagement.createPostURL;
import static com.vinted.api.automation.URLManagement.createUserURL;
import static com.vinted.api.automation.factory.PostManagementFactory.createPostRequest;
import static com.vinted.api.automation.factory.UserManagementFactory.createUserRequest;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;

public class CreatePostTest {
    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    private Long userId;
    private UserResponse userResponse;

    /**
     * Create User before each Post for that user
     */
    @BeforeEach
    public void createUser() {
        User user = createUserRequest();
        ServiceResponse serviceResponse = httpOperations.postOperation(createUserURL, user);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        userResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(userResponse);

        assertNotNull(userResponse.getData().getId());
        userId = userResponse.getData().getId();
    }

    @DisplayName("Create success post for the user")
    @Tag("smoke")
    @Test
    public void createPostSuccess() {
        CreatePostRequest createPostRequest = createPostRequest();
        String createPostEndpoint = String.format(createPostURL, userId);
        ServiceResponse serviceResponseForCreatePost = httpOperations.postOperation(createPostEndpoint, createPostRequest);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponseForCreatePost.getStatus()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponseTime()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponse()),
                () -> assertTrue(serviceResponseForCreatePost.getResponseTime() <= responseTimeLimit));

        // Assert Data
        PostResponse postResponse = gson.fromJson(serviceResponseForCreatePost.getResponse(), PostResponse.class);
        assertNotNull(postResponse);

        // Assertion of Data
        assertNull(postResponse.getMeta());

        assertNotNull(postResponse.getData().getId());
        assertEquals(userId, postResponse.getData().getUser_id());
        assertEquals(createPostRequest.getTitle(), postResponse.getData().getTitle());
        assertEquals(createPostRequest.getBody(), postResponse.getData().getBody());
    }

    @DisplayName("Negative Usecase | Post without body")
    @Test
    public void testPostWithoutBody() {
        CreatePostRequest createPostRequest = createPostRequest();
        createPostRequest.setBody(null);
        String createPostEndpoint = String.format(createPostURL, userId);
        ServiceResponse serviceResponseForCreatePost = httpOperations.postOperation(createPostEndpoint, createPostRequest);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(422, serviceResponseForCreatePost.getStatus()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponseTime()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponse()),
                () -> assertTrue(serviceResponseForCreatePost.getResponseTime() <= responseTimeLimit));

        // Assert Data
        ErrorResponse postErrorResponse = gson.fromJson(serviceResponseForCreatePost.getResponse(), ErrorResponse.class);
        assertNotNull(postErrorResponse);

        assertNull(postErrorResponse.getMeta());
        assertEquals(1, postErrorResponse.getData().size());
        assertEquals("body", postErrorResponse.getData().get(0).getField());
        assertEquals("can't be blank", postErrorResponse.getData().get(0).getMessage());
    }

    @DisplayName("Negative Usecase | Post without Title")
    @Test
    public void testPostWithoutTitle() {
        CreatePostRequest createPostRequest = createPostRequest();
        createPostRequest.setTitle(null);
        String createPostEndpoint = String.format(createPostURL, userId);
        ServiceResponse serviceResponseForCreatePost = httpOperations.postOperation(createPostEndpoint, createPostRequest);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(422, serviceResponseForCreatePost.getStatus()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponseTime()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponse()),
                () -> assertTrue(serviceResponseForCreatePost.getResponseTime() <= responseTimeLimit));

        // Assert Data
        ErrorResponse postErrorResponse = gson.fromJson(serviceResponseForCreatePost.getResponse(), ErrorResponse.class);
        assertNotNull(postErrorResponse);

        assertNull(postErrorResponse.getMeta());
        assertEquals(1, postErrorResponse.getData().size());
        assertEquals("title", postErrorResponse.getData().get(0).getField());
        assertEquals("can't be blank", postErrorResponse.getData().get(0).getMessage());

    }
}