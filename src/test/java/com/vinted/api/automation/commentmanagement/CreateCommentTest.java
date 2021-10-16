package com.vinted.api.automation.commentmanagement;

import com.google.gson.Gson;
import com.vinted.api.automation.http.HttpOperationImpl;
import com.vinted.api.automation.http.HttpOperations;
import com.vinted.api.automation.model.common.User;
import com.vinted.api.automation.model.request.comment.CreateCommentRequest;
import com.vinted.api.automation.model.request.post.CreatePostRequest;
import com.vinted.api.automation.model.response.ServiceResponse;
import com.vinted.api.automation.model.response.comment.CommentResponse;
import com.vinted.api.automation.model.response.post.PostResponse;
import com.vinted.api.automation.model.response.user.UserResponse;
import com.vinted.api.automation.usermanagment.CreateUserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.vinted.api.automation.URLManagement.*;
import static com.vinted.api.automation.factory.CommentManagementFactory.createCommentRequest;
import static com.vinted.api.automation.factory.PostManagementFactory.createPostRequest;
import static com.vinted.api.automation.factory.UserManagementFactory.createUserRequest;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;

public class CreateCommentTest {
    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    private Long userId;
    private UserResponse userResponse;
    private User createUserRequest;
    private Long postId;

    /**
     * Create User and Post before creating each comment, so that each test will be independent of other
     */
    @BeforeEach
    public void createUser() {
        createUserRequest = createUserRequest();
        ServiceResponse serviceResponse = httpOperations.postOperation(createUserURL, createUserRequest);

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

//        ------

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
        postId = postResponse.getData().getId();
    }

    @DisplayName("Create Comment successfully for the particular post for that user")
    @Tag("smoke")
    @Test
    public void createCommentForUser() {
        String commentURL = String.format(createCommentURL, userId);
        CreateCommentRequest createCommentRequest = createCommentRequest(createUserRequest.getEmail(), createUserRequest.getName(), postId);

        ServiceResponse serviceResponseForComment = httpOperations.postOperation(commentURL, createCommentRequest);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponseForComment.getStatus()),
                () -> assertNotNull(serviceResponseForComment.getResponseTime()),
                () -> assertNotNull(serviceResponseForComment.getResponse()),
                () -> assertTrue(serviceResponseForComment.getResponseTime() <= responseTimeLimit));

        CommentResponse commentResponse = gson.fromJson(serviceResponseForComment.getResponse(), CommentResponse.class);
        assertNotNull(commentResponse);
    }
}