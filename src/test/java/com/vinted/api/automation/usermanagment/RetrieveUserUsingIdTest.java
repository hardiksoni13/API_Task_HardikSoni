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
import static com.vinted.api.automation.URLManagement.deleteUserURL;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;

public class RetrieveUserUsingIdTest {

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


    @AfterEach
    public void deleteUser() {
        String deleteURI = String.format(deleteUserURL, userId);
        ServiceResponse serviceResponse = httpOperations.deleteOperation(deleteURI);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(204, serviceResponse.getStatus()));
    }

    @DisplayName("Retrieve user with userId")
    @Tag("smoke")
    @Test
    public void testRetrieveUserId() {
        String retrieveUserById = String.format(retrieveUserUsingIdURL, userId);
        ServiceResponse serviceResponse = httpOperations.getOperation(retrieveUserById);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        UserResponse retrieveUserResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(retrieveUserResponse);
        assertEquals(userResponse, retrieveUserResponse);
    }

    @DisplayName("Negative | Retrieve user with InvalidId")
    @Test
    public void testRetrieveUserIdWithWrongId() {
        String retrieveUserById = String.format(retrieveUserUsingIdURL, "123322");
        ServiceResponse serviceResponse = httpOperations.getOperation(retrieveUserById);

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