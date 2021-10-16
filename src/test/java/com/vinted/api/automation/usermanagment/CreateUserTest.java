package com.vinted.api.automation.usermanagment;

import com.google.gson.Gson;
import com.vinted.api.automation.factory.UserManagementFactory;
import com.vinted.api.automation.http.HttpOperationImpl;
import com.vinted.api.automation.http.HttpOperations;
import com.vinted.api.automation.model.common.User;
import com.vinted.api.automation.model.response.user.UserResponse;
import com.vinted.api.automation.model.response.ErrorResponse;
import com.vinted.api.automation.model.response.ServiceResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.vinted.api.automation.URLManagement.createUserURL;
import static com.vinted.api.automation.URLManagement.deleteUserURL;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTest {

    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    Long userId;

    /**
     * Delete create user, so that database wont hold testing data
     */
    @AfterEach
    public void deleteUser() {
        String deleteURI = String.format(deleteUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.deleteOperation(deleteURI);
        
    // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(204, serviceResponse.getStatus()));
    }

    @DisplayName("User created successfully")
    @Tag("smoke")
    @Test
    public void testCreateUser() {
        User user = UserManagementFactory.createUserRequest();
        ServiceResponse serviceResponse = httpOperations.postOperation(createUserURL, user);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        UserResponse userResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(userResponse);

        assertNull(userResponse.getMeta());

        assertNotNull(userResponse.getData().getId());
        userId = userResponse.getData().getId();

        assertEquals(user.getEmail(), userResponse.getData().getEmail());
        assertEquals(user.getName(), userResponse.getData().getName());
        assertEquals(user.getGender(), userResponse.getData().getGender());
        assertEquals(user.getStatus(), userResponse.getData().getStatus());

        logger.info("Created User which is having emailId as {}", user.getEmail());
    }

    @DisplayName("User already exist")
    @Test
    public void testNegativeAlreadyExistingUser() {
        User user = UserManagementFactory.createUserRequest();
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

//        -----------Create user having same emailId------------------
        User existingUser = UserManagementFactory.createUserRequest();
        existingUser.setEmail(user.getEmail());
        ServiceResponse existingUserResponse = httpOperations.postOperation(createUserURL, existingUser);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponse.getStatus()),  // Ideally it should be 400
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        ErrorResponse errorResponse = gson.fromJson(existingUserResponse.getResponse(), ErrorResponse.class);
        assertNotNull(errorResponse);

        assertNull(errorResponse.getMeta());

        assertEquals(1, errorResponse.getData().size());
        assertEquals("email", errorResponse.getData().get(0).getField());
        assertEquals("has already been taken", errorResponse.getData().get(0).getMessage());
    }
}