package com.vinted.api.automation.usermanagment;

import com.google.gson.Gson;
import com.vinted.api.automation.factory.UserManagementFactory;
import com.vinted.api.automation.http.HttpOperationImpl;
import com.vinted.api.automation.http.HttpOperations;
import com.vinted.api.automation.model.common.User;
import com.vinted.api.automation.model.response.*;
import com.vinted.api.automation.model.response.user.Data;
import com.vinted.api.automation.model.response.user.RetrieveAllUserResponse;
import com.vinted.api.automation.model.response.user.UserResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static com.vinted.api.automation.URLManagement.*;
import static com.vinted.api.automation.URLManagement.deleteUserURL;
import static com.vinted.api.automation.utils.PropertyReader.getResponseTimeLimit;
import static org.junit.jupiter.api.Assertions.*;

public class RetrieveAllUsers {
    private HttpOperations httpOperations = new HttpOperationImpl();
    private Long responseTimeLimit = getResponseTimeLimit();
    private Gson gson = new Gson();
    private static Logger logger = LogManager.getLogger(CreateUserTest.class);
    private List<UserResponse> userResponses = new ArrayList<>();

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

        UserResponse userResponse = gson.fromJson(serviceResponse.getResponse(), UserResponse.class);
        assertNotNull(userResponse);

        assertNotNull(userResponse.getData().getId());
        userResponses.add(userResponse);
    }

    @AfterEach
    public void deleteUser() {
        userResponses.forEach(userResponse ->  {
                    String deleteURI = String.format(deleteUserURL, userResponse.getData().getId());
                    ServiceResponse serviceResponse = httpOperations.deleteOperation(deleteURI);

                    // Assertions
                    assertAll("Assertion for Status, responseData and responseTime",
                            () -> assertEquals(204, serviceResponse.getStatus()));
                }
        );
    }

    @DisplayName("Retrieve single user")
    @Test
    public void testRetrieveUserSingle() {
        ServiceResponse serviceResponse = httpOperations.getOperation(retrieveAllUsersURL);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        RetrieveAllUserResponse retrieveAllUsers = gson.fromJson(serviceResponse.getResponse(), RetrieveAllUserResponse.class);
        assertNotNull(retrieveAllUsers);

        assertRetrieveUsers(retrieveAllUsers);
    }

    @DisplayName("Retrieve All users")
    @Tag("smoke")
    @Test
    public void testRetrieveUserHavingMultiple() {
        // Lets add another user
        User secondUser = UserManagementFactory.createUserRequest();
        ServiceResponse serviceResponseSecondUser = httpOperations.postOperation(createUserURL, secondUser);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(201, serviceResponseSecondUser.getStatus()),
                () -> assertNotNull(serviceResponseSecondUser.getResponseTime()),
                () -> assertNotNull(serviceResponseSecondUser.getResponse()),
                () -> assertTrue(serviceResponseSecondUser.getResponseTime() <= responseTimeLimit));

        UserResponse secondUserResponse = gson.fromJson(serviceResponseSecondUser.getResponse(), UserResponse.class);
        assertNotNull(secondUserResponse);

        assertNotNull(secondUserResponse.getData().getId());
        userResponses.add(secondUserResponse);

        // Now lets see retrieveAll uses
        ServiceResponse serviceResponse = httpOperations.getOperation(retrieveAllUsersURL);

        // Assertions
        assertAll("Assertion for Status, responseData and responseTime",
                () -> assertEquals(200, serviceResponse.getStatus()),
                () -> assertNotNull(serviceResponse.getResponseTime()),
                () -> assertNotNull(serviceResponse.getResponse()),
                () -> assertTrue(serviceResponse.getResponseTime() <= responseTimeLimit));

        RetrieveAllUserResponse retrieveAllUsers = gson.fromJson(serviceResponse.getResponse(), RetrieveAllUserResponse.class);
        assertNotNull(retrieveAllUsers);

        assertRetrieveUsers(retrieveAllUsers);

    }

    private void assertRetrieveUsers(RetrieveAllUserResponse retrieveAllUsers) {
        // Assert Meta
        assertNotNull(retrieveAllUsers.getMeta());
        Pagination pagination = retrieveAllUsers.getMeta().getPagination();
        assertNotNull(pagination);
        assertNotNull(pagination.getTotal());
        assertNotNull(pagination.getPage());
        assertNotNull(pagination.getPages());
        assertNotNull(pagination.getLimit());
        assertNotNull(pagination.getTotal());
        assertNotNull(pagination.getLinks());
        assertNull(pagination.getLinks().getPrevious());
        assertNotNull(pagination.getLinks().getCurrent());
        assertNotNull(pagination.getLinks().getNext());

        // Data
        assertTrue(retrieveAllUsers.getData().size() >= 1);
        List<Data> users = retrieveAllUsers.getData();

        users.containsAll(userResponses);
    }
}