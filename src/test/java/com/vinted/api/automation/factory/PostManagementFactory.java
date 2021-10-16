package com.vinted.api.automation.factory;

import com.vinted.api.automation.model.request.post.CreatePostRequest;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class PostManagementFactory {

    /**
     * This method will be used to get filled request for adding a Post.
     * At Test level, tester can modify it as per there need.
     * Reason : Such factories will avoid duplicate work at each test level.
     * @return CreatePostRequest
     */
    public static CreatePostRequest createPostRequest() {
        String title = "This is title".concat(randomAlphabetic(5));
        String body = "This is my first post which is having body as this".concat(randomAlphanumeric(20));

        CreatePostRequest createPostRequest = new CreatePostRequest(title, body);

        return createPostRequest;
    }
}
