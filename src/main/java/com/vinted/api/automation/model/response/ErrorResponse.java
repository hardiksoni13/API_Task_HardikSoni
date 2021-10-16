package com.vinted.api.automation.model.response;

import java.util.List;

/**
 * This will help to create error response
 */
public class ErrorResponse {

    private Meta meta;
    private List<ErrorData> data;

    public ErrorResponse() {

    }

    public ErrorResponse(Meta meta, List<ErrorData> data) {
        this.meta = meta;
        this.data = data;
    }

    public ErrorResponse setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public ErrorResponse setData(List<ErrorData> data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<ErrorData> getData() {
        return data;
    }
}
