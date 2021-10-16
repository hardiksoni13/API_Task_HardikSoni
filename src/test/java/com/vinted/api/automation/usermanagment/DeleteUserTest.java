package com.vinted.api.automation.usermanagment;

import com.google.gson.Gson;
import com.vinted.api.automation.factory.UserManagementFactory;
import com.vinted.api.automation.http.HttpOperationImpl;
import com.vinted.api.automation.http.HttpOperations;
import com.vinted.api.automation.model.common.User;
import com.vinted.api.automation.model.response.ErrorResponseSpecificUser;
import com.vinted.api.automation.model.response.ServiceResponse;
import com.vinted.api.automation.model.response.user.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.vinted.api.automation.URLManagement.createUserURL;
import static com.vinted.api.automation.URLManagement.deleteUserURL;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteUserTest {
    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    private Long userId;
    private UserResponse userResponse;


    @BeforeEach
    public void createUser() {
        User user = UserManagementFactory.createUserRequest();
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

    @DisplayName("Delete user succesfully")
    @Tag("smoke")
    @Test
    public void testDeleteUser() {
        String deleteURI = String.format(deleteUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.deleteOperation(deleteURI);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(204, serviceResponse.getStatus()));
    }

    @DisplayName("Delete user with invalid Id")
    @Test
    public void testDeleteUserWithInvalidId() {
        String deleteURI = String.format(deleteUserURL, "123Test");
        ServiceResponse serviceResponse = httpOperations.deleteOperation(deleteURI);

        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(404, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        ErrorResponseSpecificUser errorResponse = gson.fromJson(serviceResponse.getResponse(), ErrorResponseSpecificUser.class);
        assertNotNull(errorResponse);

        assertNull(errorResponse.getMeta());

        assertNull(errorResponse.getMeta());
        assertEquals("Resource not found", errorResponse.getData().getMessage());
    }
}