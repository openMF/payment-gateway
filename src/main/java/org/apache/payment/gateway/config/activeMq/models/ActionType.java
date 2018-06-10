package org.apache.payment.gateway.config.activeMq.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Rahul Goel created on 10/6/18
 */


@JsonFormat
public enum ActionType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE"),
    DEFAULT("DEFAULT");

    private String val;

    private ActionType(String val) {
        this.val = val;
    }

    @JsonValue
    public String getValue() {
        return this.val;
    }

    @JsonCreator
    public static ActionType fromValue(String value) {
        if (value.equalsIgnoreCase(CREATE.getValue())) {
            return CREATE;
        } else if (value.equalsIgnoreCase(UPDATE.getValue())) {
            return UPDATE;
        } else {
            return value.equalsIgnoreCase(DELETE.getValue()) ? DELETE : DEFAULT;
        }
    }
}