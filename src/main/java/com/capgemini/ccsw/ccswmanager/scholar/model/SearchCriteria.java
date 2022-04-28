package com.capgemini.ccsw.ccswmanager.scholar.model;

/**
 * @author jchengli
 *
 */

public class SearchCriteria {
    private String key;
    private String operation;
    private Object firstValue;
    private Object secondValue;

    public SearchCriteria(String key, String operation, Object firstValue, Object secondValue) {
        this.key = key;
        this.operation = operation;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
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
     * @return the firstValue
     */
    public Object getFirstValue() {
        return firstValue;
    }

    /**
     * @param firstValue the firstValue to set
     */
    public void setFirstValue(Object firstValue) {
        this.firstValue = firstValue;
    }

    /**
     * @return the secondValue
     */
    public Object getSecondValue() {
        return secondValue;
    }

    /**
     * @param secondValue the secondValue to set
     */
    public void setSecondValue(Object secondValue) {
        this.secondValue = secondValue;
    }

}
