package com.vinted.api.automation.model.response.user;

import com.vinted.api.automation.model.response.Meta;

import java.util.Objects;

public class UserResponse {
    private Meta meta;
    private Data data;

    public UserResponse() {

    }

    public UserResponse(Meta meta, Data data) {
        this.meta = meta;
        this.data = data;
    }

    public UserResponse setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public UserResponse setData(Data data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Data getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserResponse)) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(getMeta(), that.getMeta()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMeta(), getData());
    }
}