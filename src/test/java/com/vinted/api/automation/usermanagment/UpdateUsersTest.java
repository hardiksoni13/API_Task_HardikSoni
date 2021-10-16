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
import org.junit.jupiter.api.*;

import static com.vinted.api.automation.URLManagement.*;
import static com.vinted.api.automation.URLManagement.updateUserURL;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateUsersTest {
    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    private Long userId;
    private User user;

    @BeforeEach
    public void createUser() {
        user = UserManagementFactory.createUserRequest();
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
    }

    @AfterEach
    public void deleteUser() {
        String deleteURI = String.format(deleteUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.deleteOperation(deleteURI);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(204, serviceResponse.getStatus()));
    }

    @DisplayName("Update Name of the user")
    @Tag("smoke")
    @Test
    public void updateNameOfTheUser() {
        String existingUserName = user.getName();
        user.setName("Update".concat(existingUserName));

        String updatedUserURI = String.format(updateUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.updateOperation(updatedUserURI, user);
        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        UserResponse updatedUserResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(updatedUserResponse);

        assertNotNull(updatedUserResponse.getData().getId());
        assertEquals(userId, updatedUserResponse.getData().getId());

        assertEquals(user.getEmail(), updatedUserResponse.getData().getEmail());
        assertEquals(user.getName(), updatedUserResponse.getData().getName());
        assertEquals(user.getGender(), updatedUserResponse.getData().getGender());
        assertEquals(user.getStatus(), updatedUserResponse.getData().getStatus());
    }

    @DisplayName("Update Gender of the user")
    @Test
    public void updateGenderOfTheUser() {
        String existingUserGender = user.getGender();
        String updatedGender = existingUserGender.equalsIgnoreCase("male") ? "female" : "male";
        user.setGender(updatedGender);

        String updatedUserURI = String.format(updateUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.updateOperation(updatedUserURI, user);
        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        UserResponse updatedUserResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(updatedUserResponse);

        assertNotNull(updatedUserResponse.getData().getId());
        assertEquals(userId, updatedUserResponse.getData().getId());

        assertEquals(user.getEmail(), updatedUserResponse.getData().getEmail());
        assertEquals(user.getName(), updatedUserResponse.getData().getName());
        assertEquals(user.getGender(), updatedUserResponse.getData().getGender());
        assertEquals(user.getStatus(), updatedUserResponse.getData().getStatus());
    }

    @DisplayName("Update Status of the user")
    @Test
    public void updateStatusOfTheUser() {
        String existingUserStatus = user.getStatus();
        String updatedStatus = existingUserStatus.equalsIgnoreCase("active") ? "inactive" : "active";
        user.setStatus(updatedStatus);

        String updatedUserURI = String.format(updateUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.updateOperation(updatedUserURI, user);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        UserResponse updatedUserResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(updatedUserResponse);

        assertNotNull(updatedUserResponse.getData().getId());
        assertEquals(userId, updatedUserResponse.getData().getId());

        assertEquals(user.getEmail(), updatedUserResponse.getData().getEmail());
        assertEquals(user.getName(), updatedUserResponse.getData().getName());
        assertEquals(user.getGender(), updatedUserResponse.getData().getGender());
        assertEquals(user.getStatus(), updatedUserResponse.getData().getStatus());
    }

    @DisplayName("Negative | Update with invalid UserId")
    @Test
    public void testWithInvalidUserIdForUpdate() {
        String updatedUserURI = String.format(updateUserURL, "2323rwef");
        ServiceResponse serviceResponse = httpOperations.updateOperation(updatedUserURI, user);

        // Assertions
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