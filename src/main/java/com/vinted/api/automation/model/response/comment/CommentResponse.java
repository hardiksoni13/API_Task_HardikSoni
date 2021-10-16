package com.vinted.api.automation.model.response.comment;

import com.vinted.api.automation.model.response.Meta;
/**
 * This is Data Transfer Object class which will hold the data of type CommentResponse, which will help
 * to keep comment response data
 *  1. We have used Builder Pattern over here to create the object, along with Getter methods.
 *  2. We have override "equals" and "hashCode" method, so that we can compare object using its values.
 * Note : We can use Lombok annotations which will work same way just by adding
 * annotation at class level. --> https://projectlombok.org/features/all
 */
public class CommentResponse {
    private Meta meta;
    private CreateCommentResponse data;

    CommentResponse() {
    }

    CommentResponse(Meta meta, CreateCommentResponse data) {
        this.meta = meta;
        this.data = data;
    }

    public CommentResponse setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public CommentResponse setData(CreateCommentResponse data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public CreateCommentResponse getData() {
        return data;
    }
}