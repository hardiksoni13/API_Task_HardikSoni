package com.vinted.api.automation.model.response.user;

import java.util.Objects;

public class Links {
    private String previous;
    private String current;
    private String next;

    public Links() {
    }

    public Links(String previous, String current, String next) {
        this.previous = previous;
        this.current = current;
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public String getCurrent() {
        return current;
    }

    public String getNext() {
        return next;
    }

    public Links setPrevious(String previous) {
        this.previous = previous;
        return this;
    }

    public Links setCurrent(String current) {
        this.current = current;
        return this;
    }

    public Links setNext(String next) {
        this.next = next;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Links)) return false;
        Links links = (Links) o;
        return Objects.equals(getPrevious(), links.getPrevious()) && Objects.equals(getCurrent(), links.getCurrent()) && Objects.equals(getNext(), links.getNext());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrevious(), getCurrent(), getNext());
    }
}