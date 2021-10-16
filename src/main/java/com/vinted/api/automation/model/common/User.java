package com.vinted.api.automation.model.common;

/**
 * This is Data Transfer Object class which will hold the data of type User.
 *  1. We have used Builder Pattern over here to create the object, along with Getter methods.
 *  2. We have override "equals" and "hashCode" method, so that we can compare object using its values.
 * Note : We can use Lombok annotations which will work same way just by adding
 * annotation at class level. --> https://projectlombok.org/features/all
 */

public class User {
    private String name;
    private String email;
    private String gender;
    private String status;

    public User() {
    }

    public User(String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public User setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }
}