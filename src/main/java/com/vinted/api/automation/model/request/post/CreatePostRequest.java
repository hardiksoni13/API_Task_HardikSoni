package com.vinted.api.automation.model.request.post;

/**
 * This is Data Transfer Object class which will hold the data of type CreatePostRequest, which will help
 * to keep post request data
 *  1. We have used Builder Pattern over here to create the object, along with Getter methods.
 *  2. We have override "equals" and "hashCode" method, so that we can compare object using its values.
 * Note : We can use Lombok annotations which will work same way just by adding
 * annotation at class level. --> https://projectlombok.org/features/all
 */
public class CreatePostRequest {
    private String title;
    private String body;

    public CreatePostRequest() {

    }

    public CreatePostRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public CreatePostRequest setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreatePostRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}