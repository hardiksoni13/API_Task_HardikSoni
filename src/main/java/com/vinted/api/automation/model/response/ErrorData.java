package com.vinted.api.automation.model.response;

/**
 * This custom object will be used to create Error data object
 */
public class ErrorData {
    private String field;
    private String message;

    public ErrorData() {}

    public ErrorData(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ErrorData setField(String field) {
        this.field = field;
        return this;
    }

    public ErrorData setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
