package com.vinted.api.automation.http;
import com.vinted.api.automation.model.response.ServiceResponse;

/**
 * For execution of Post/Get/Put/etc.. methods we have created an interface,
 * and implementation is in another call.
 * Reason: In future if we want to change the backend library i.e. RestAssured, Test wont get affected by this.
 */
public interface HttpOperations {
    ServiceResponse getOperation(String URL);
    ServiceResponse postOperation(String URL, Object body);
    ServiceResponse updateOperation(String URL, Object body);
    ServiceResponse deleteOperation(String URL);
}
