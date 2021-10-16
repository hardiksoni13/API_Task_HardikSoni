package com.vinted.api.automation.postmanagement;

import com.google.gson.Gson;
import com.vinted.api.automation.http.HttpOperationImpl;
import com.vinted.api.automation.http.HttpOperations;
import com.vinted.api.automation.model.common.User;
import com.vinted.api.automation.model.request.post.CreatePostRequest;
import com.vinted.api.automation.model.response.ServiceResponse;
import com.vinted.api.automation.model.response.post.PostData;
import com.vinted.api.automation.model.response.post.PostResponse;
import com.vinted.api.automation.model.response.post.RetrievePostResponse;
import com.vinted.api.automation.model.response.user.UserResponse;
import com.vinted.api.automation.usermanagment.CreateUserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.vinted.api.automation.URLManagement.*;
import static com.vinted.api.automation.factory.PostManagementFactory.createPostRequest;
import static com.vinted.api.automation.factory.UserManagementFactory.createUserRequest;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;

public class RetrieveAllPosts {
    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    private Long userId;
    private List<PostResponse> retrievePostByIdResponses = new ArrayList<>();

    /**
     * Create user and post before retrieving All posts
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

        UserResponse userResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(userResponse);

        assertNotNull(userResponse.getData().getId());
        userId = userResponse.getData().getId();

        // Create Post
        CreatePostRequest createPostRequest = createPostRequest();
        String createPostEndpoint = String.format(createPostURL, userId);
        ServiceResponse serviceResponseForCreatePost = httpOperations.postOperation(createPostEndpoint, createPostRequest);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponseForCreatePost.getStatus()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponseTime()),
                () -> assertNotNull(serviceResponseForCreatePost.getResponse()),
                () -> assertTrue(serviceResponseForCreatePost.getResponseTime() <= responseTimeLimit));

        PostResponse retrievePostByIdResponse = gson.fromJson(serviceResponseForCreatePost.getResponse(), PostResponse.class);
        retrievePostByIdResponses.add(retrievePostByIdResponse);
    }

    @DisplayName("Try to retrieve post successfully")
    @Tag("smoke")
    @Test
    public void retrieveAllPost() {
        ServiceResponse serviceResponse = httpOperations.getOperation(retrieveAllPost);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        RetrievePostResponse retrieveAllPostResponse = gson.fromJson(serviceResponse.getResponse(), RetrievePostResponse.class);
        assertNotNull(retrieveAllPostResponse);

        // Assert Meta data
        assertNotNull(retrieveAllPostResponse.getMeta());
        assertTrue(retrieveAllPostResponse.getData().size() >= 1);

        List<PostData> data = new ArrayList<>();
        retrievePostByIdResponses.forEach(post -> data.add(post.getData()));
        assertTrue(retrieveAllPostResponse.getData().contains(data));  // Defect newly created post is not coming

    }
}