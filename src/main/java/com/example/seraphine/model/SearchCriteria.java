package com.example.seraphine.model;

import java.util.Objects;

public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchCriteria)) return false;
        SearchCriteria that = (SearchCriteria) o;
        return Objects.equals(getKey(), that.getKey()) && Objects.equals(getValue(), that.getValue()) && getOperation() == that.getOperation();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue(), getOperation());
    }
}
