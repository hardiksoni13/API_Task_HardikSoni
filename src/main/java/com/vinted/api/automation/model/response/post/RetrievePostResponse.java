package com.vinted.api.automation.model.response.post;

import com.vinted.api.automation.model.response.Meta;

import java.util.List;
import java.util.Objects;

/**
 * Retrieve post response data
 */
public class RetrievePostResponse {
    private Meta meta;
    private List<PostData> data;

    public RetrievePostResponse() {}

    public RetrievePostResponse(Meta meta, List<PostData> data) {
        this.meta = meta;
        this.data = data;
    }

    public RetrievePostResponse setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public RetrievePostResponse setData(List<PostData> data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<PostData> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RetrievePostResponse)) return false;
        RetrievePostResponse that = (RetrievePostResponse) o;
        return Objects.equals(getMeta(), that.getMeta()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMeta(), getData());
    }
}