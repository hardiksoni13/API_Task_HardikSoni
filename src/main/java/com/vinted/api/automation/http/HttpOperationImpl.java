package com.vinted.api.automation.http;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import com.vinted.api.automation.utils.PropertyReader;
import com.vinted.api.automation.model.response.ServiceResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation has been created using RestAssured library.
 */
public class HttpOperationImpl implements HttpOperations {

    Gson gson = new Gson();
    String contentType = "application/json";
    String contentTypeKey = "Content-type";
    String host = PropertyReader.getHostValue();
    String authorizeKey = "Authorization";
    String authorizeToken = PropertyReader.getAuthorizeToken();

    /**
     * This method will be used to perform GET operations
     *
     * @param URL
     * @return ServiceResponse : custom Response class
     */
    @Override
    public ServiceResponse getOperation(String URL) {
        Response restAssuredResponse = RestAssured.given().contentType(contentType)
                .headers(getHeaders()).when().log().all().get(host.concat(URL));
        return convertToServiceResponse(restAssuredResponse);
    }

    /**
     * This method will be used to perform POST operations
     *
     * @param URL
     * @param body
     * @return ServiceResponse : custom Response class
     */
    @Override
    public ServiceResponse postOperation(String URL, Object body) {
        Response restAssuredResponse = RestAssured.given().contentType(contentType)
                .headers(getHeaders()).body(gson.toJson(body)).when().log().all().post(host.concat(URL));
        return convertToServiceResponse(restAssuredResponse);
    }

    /**
     * This method will be used to perform PUT operations
     *
     * @param URL
     * @param body
     * @return ServiceResponse : custom Response class
     */
    @Override
    public ServiceResponse updateOperation(String URL, Object body) {
        Response restAssuredResponse = RestAssured.given().contentType(contentType)
                .headers(getHeaders()).body(gson.toJson(body)).when().log().all().put(host.concat(URL));
        return convertToServiceResponse(restAssuredResponse);
    }

    /**
     * This method will be used to perform delete operations
     *
     * @param URL
     * @return ServiceResponse : custom Response class
     */
    @Override
    public ServiceResponse deleteOperation(String URL) {
        Response restAssuredResponse = RestAssured.given().contentType(contentType)
                .headers(getHeaders()).when().log().all().delete(host.concat(URL));
        return convertToServiceResponse(restAssuredResponse);
    }


    /**
     * This method will be used to convert RESTASSURED specific response to custom response class
     *
     * @param restAssuredResponse
     * @return ServiceResponse : custom Response class
     */
    private ServiceResponse convertToServiceResponse(Response restAssuredResponse) {
        Map<String, String> responseHeaders = new HashMap<>();
        restAssuredResponse.getHeaders().forEach(header -> responseHeaders.put(header.getName(), header.getValue()));

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setResponse(restAssuredResponse.getBody().prettyPrint())
                .setResponseTime(restAssuredResponse.getTime()).setStatus(restAssuredResponse.getStatusCode())
                .setResponseHeaders(responseHeaders);

        return serviceResponse;
    }

    /**
     * We have added common headers required for service execution, in this method
     *
     * @return
     */
    private Headers getHeaders() {
        Header authorize = new Header(authorizeKey, authorizeToken);
        Header content = new Header(contentTypeKey, contentType);

        return new Headers(authorize, content);
    }
}