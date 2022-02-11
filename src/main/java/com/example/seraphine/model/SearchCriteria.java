package com.example.seraphine.model;

import java.util.Objects;

/**
 * Create criteria to search for doctor, example: search doctor based on health problem, and places
 * @author Vinh Truong Canh Thanh
 */

public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    /**
     * Search criteria.
     * @param key
     * @param value
     * @param operation
     */
    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    /**
     * Getter and Setter
     * @author Vinh Truong Canh Thanh
     */
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
