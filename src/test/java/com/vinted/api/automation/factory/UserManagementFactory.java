package com.vinted.api.automation.factory;

import com.vinted.api.automation.model.common.User;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class UserManagementFactory {

    /**
     * This method will be used to get filled request for adding a user.
     * At Test level, tester can modify it as per there need.
     * Reason : Such factories will avoid duplicate work at each test level.
     * @return User
     */
    public static User createUserRequest() {
        User user = new User();
        String name =  randomAlphabetic(5);
        String email = randomAlphanumeric(5).concat("@gmail.com");

        user.setName(name).setEmail(email).setGender("male").setStatus("active");

        return user;
    }
}