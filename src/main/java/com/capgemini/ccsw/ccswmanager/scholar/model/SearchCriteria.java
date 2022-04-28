package com.capgemini.ccsw.ccswmanager.scholar.model;

/**
 * @author jchengli
 *
 */

public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private Object value2;

    public SearchCriteria(String key, String operation, Object value, Object value2) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.value2 = value2;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the value2
     */
    public Object getValue2() {
        return value2;
    }

    /**
     * @param value2 the value2 to set
     */
    public void setValue2(Object value2) {
        this.value2 = value2;
    }

}
