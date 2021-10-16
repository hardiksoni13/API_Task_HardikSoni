package com.vinted.api.automation.model.response.user;

import java.util.Objects;

public class Data {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public Data() {

    }
    public Data(Long id, String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public Data setId(Long id) {
        this.id = id;
        return this;
    }
    public Data setName(String name) {
        this.name = name;
        return this;
    }

    public Data setEmail(String email) {
        this.email = email;
        return this;
    }

    public Data setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Data setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return Objects.equals(getId(), data.getId()) && Objects.equals(getName(), data.getName()) && Objects.equals(getEmail(), data.getEmail()) && Objects.equals(getGender(), data.getGender()) && Objects.equals(getStatus(), data.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getGender(), getStatus());
    }
}