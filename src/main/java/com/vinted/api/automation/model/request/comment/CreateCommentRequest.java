package com.vinted.api.automation.model.request.comment;

/**
 * This is Data Transfer Object class which will hold the data of type CreateCommentRequest, which will help
 * to keep comment request data
 *  1. We have used Builder Pattern over here to create the object, along with Getter methods.
 *  2. We have override "equals" and "hashCode" method, so that we can compare object using its values.
 * Note : We can use Lombok annotations which will work same way just by adding
 * annotation at class level. --> https://projectlombok.org/features/all
 */

public class CreateCommentRequest {
    private String name;
    private String email;
    private String body;
    private Long post;

    public Long getPost_id() {
        return post;
    }

    public CreateCommentRequest setPost_id(Long post_id) {
        this.post = post_id;
        return this;
    }


    public CreateCommentRequest setName(String name) {
        this.name = name;
        return this;
    }

    public CreateCommentRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateCommentRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public CreateCommentRequest() {
    }

    public CreateCommentRequest(String name, String email, String body, Long post) {
        this.name = name;
        this.email = email;
        this.body = body;
        this.post = post;
    }
}