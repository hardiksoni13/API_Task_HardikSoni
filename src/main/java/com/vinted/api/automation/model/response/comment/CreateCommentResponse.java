package com.vinted.api.automation.model.response.comment;

/**
 * Create comment response custom data type object
 */
public class CreateCommentResponse {
    private Long id;
    private Long post_id;
    private String name;
    private String email;
    private String body;

    CreateCommentResponse() {
    }

    CreateCommentResponse(Long id, Long post_id, String name, String email, String body) {
        this.id = id;
        this.post_id = post_id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public CreateCommentResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getPost_id() {
        return post_id;
    }

    public CreateCommentResponse setPost_id(Long post_id) {
        this.post_id = post_id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateCommentResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateCommentResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBody() {
        return body;
    }

    public CreateCommentResponse setBody(String body) {
        this.body = body;
        return this;
    }
}
