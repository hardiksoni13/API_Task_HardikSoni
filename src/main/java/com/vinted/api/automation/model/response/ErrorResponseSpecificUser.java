package com.vinted.api.automation.model.response;

public class ErrorResponseSpecificUser {
    private Meta meta;
    private ErrorData data;

    public ErrorResponseSpecificUser() {
    }

    public ErrorResponseSpecificUser(Meta meta, ErrorData data) {
        this.meta = meta;
        this.data = data;
    }

    public ErrorResponseSpecificUser setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public ErrorResponseSpecificUser setData(ErrorData data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public ErrorData getData() {
        return data;
    }
}