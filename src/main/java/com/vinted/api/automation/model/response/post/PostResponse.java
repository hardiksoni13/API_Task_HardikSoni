package com.vinted.api.automation.model.response.post;

import com.vinted.api.automation.model.response.Meta;

import java.util.Objects;

/**
 * Create post response data
 */
public class PostResponse {
    private Meta meta;
    private PostData data;

    public PostResponse() {
    }

    public PostResponse(Meta meta, PostData data) {
        this.meta = meta;
        this.data = data;
    }

    public PostResponse setMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public PostResponse setData(PostData data) {
        this.data = data;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public PostData getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostResponse)) return false;
        PostResponse that = (PostResponse) o;
        return Objects.equals(getMeta(), that.getMeta()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMeta(), getData());
    }
}