package com.vinted.api.automation.model.response;

import com.vinted.api.automation.model.response.user.Links;

import java.util.Objects;

public class Pagination {
    private Long total;
    private Long pages;
    private Integer page;
    private Long limit;
    private Links links;

    public Pagination() {
    }

    public Pagination(Long total, Long pages, Integer page, Long limit, Links links) {
        this.total = total;
        this.pages = pages;
        this.page = page;
        this.limit = limit;
        this.links = links;
    }

    public Long getTotal() {
        return total;
    }

    public Long getPages() {
        return pages;
    }

    public Integer getPage() {
        return page;
    }

    public Long getLimit() {
        return limit;
    }

    public Links getLinks() {
        return links;
    }

    public Pagination setPages(Long pages) {
        this.pages = pages;
        return this;
    }

    public Pagination setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Pagination setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Pagination setLimit(Long limit) {
        this.limit = limit;
        return this;
    }

    public Pagination setLinks(Links links) {
        this.links = links;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagination)) return false;
        Pagination that = (Pagination) o;
        return getPage() == that.getPage() && Objects.equals(getTotal(), that.getTotal()) && Objects.equals(getPages(), that.getPages()) && Objects.equals(getLimit(), that.getLimit()) && Objects.equals(getLinks(), that.getLinks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotal(), getPages(), getPage(), getLimit(), getLinks());
    }
}
