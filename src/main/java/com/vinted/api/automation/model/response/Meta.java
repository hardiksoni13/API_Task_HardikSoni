package com.vinted.api.automation.model.response;

import java.util.Objects;

public class Meta {
    private Pagination pagination;

    public Meta() {};
    public Meta(Pagination pagination) {
        this.pagination = pagination;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public Meta setPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meta)) return false;
        Meta meta = (Meta) o;
        return Objects.equals(getPagination(), meta.getPagination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPagination());
    }
}
