package com.vinted.api.automation.model.response.user;

import com.vinted.api.automation.model.response.Meta;

import java.util.List;

public class RetrieveAllUserResponse {
    private Meta meta;
    private List<Data> data;

    public RetrieveAllUserResponse() {
    }

    public RetrieveAllUserResponse(Meta meta, List<Data> data) {
        this.meta = meta;
        this.data = data;
    }

    public RetrieveAllUserResponse setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public RetrieveAllUserResponse setData(List<Data> data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<Data> getData() {
        return data;
    }
}