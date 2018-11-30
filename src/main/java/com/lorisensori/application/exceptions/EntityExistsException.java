package com.lorisensori.application.exceptions;

public class EntityExistsException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public EntityExistsException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exist with %s : '%s' ", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
