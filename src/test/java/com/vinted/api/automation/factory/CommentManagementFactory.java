package com.vinted.api.automation.factory;

import com.vinted.api.automation.model.request.comment.CreateCommentRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class CommentManagementFactory {
    /**
     * This method will be used to get filled request for adding a Comment.
     * At Test level, tester can modify it as per there need.
     * Reason : Such factories will avoid duplicate work at each test level.
     * @return CreateCommentRequest
     */
    public static CreateCommentRequest createCommentRequest(String emailId, String name, Long postId) {
        CreateCommentRequest createCommentRequest = new CreateCommentRequest();
        String body = RandomStringUtils.randomAlphanumeric(50);
        createCommentRequest.setEmail(emailId).setBody(body).setName(name).setPost_id(postId);

        return createCommentRequest;
    }
}
