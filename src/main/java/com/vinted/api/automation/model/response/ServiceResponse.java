package com.vinted.api.automation.model.response;
import java.util.Map;

/**
 * This is the custom response class created, so that at test level custom data gets passed
 * and not Library specific i.e. RestAssured.
 * This will give us strength, if in future we want to change the library testcases won't get affected.
 *
 */

public class ServiceResponse {
    private int status;
    private String response;
    private Map<String, String> responseHeaders;
    private Long responseTime;

    public ServiceResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public ServiceResponse setResponse(String response) {
        this.response = response;
        return this;
    }

    public ServiceResponse setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    public ServiceResponse setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public Long getResponseTime() {
        return responseTime;
    }
}
