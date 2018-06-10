package org.apache.payment.gateway.utils;

/**
 * @author Rahul Goel created on 10/6/18
 */


public enum ErrorCodes {

    ACTIVEMQ_001("ACTIVEMQ_001", "Error while establishing connection with activeMq");

    private final String code;
    private final String description;

    private ErrorCodes(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
