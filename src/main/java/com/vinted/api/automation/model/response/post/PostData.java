package com.vinted.api.automation.model.response.post;

import java.util.Objects;

public class PostData {
    private Long id;
    private Long user_id;
    private String title;
    private String body;

    public PostData() {
    }

    public PostData(Long id, Long user_id, String title, String body) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.body = body;
    }

    public PostData setId(Long id) {
        this.id = id;
        return this;
    }

    public PostData setUser_id(Long user_id) {
        this.user_id = user_id;
        return this;
    }

    public PostData setTitle(String title) {
        this.title = title;
        return this;
    }

    public PostData setBody(String body) {
        this.body = body;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostData)) return false;
        PostData postData = (PostData) o;
        return Objects.equals(getId(), postData.getId()) && Objects.equals(getUser_id(), postData.getUser_id()) && Objects.equals(getTitle(), postData.getTitle()) && Objects.equals(getBody(), postData.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser_id(), getTitle(), getBody());
    }
}
