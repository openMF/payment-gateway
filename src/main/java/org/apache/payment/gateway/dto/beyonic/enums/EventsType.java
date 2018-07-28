package org.apache.payment.gateway.dto.beyonic.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Sanyam Goel created on 28/7/18
 */
public enum EventsType {

    PAYMENT_STATUS_CHANGED("payment.status.changed"),
    COLLECTION_RECEIVED("collection.received"),
    CONTACT_CREATED("contact.created"),
    COLLECTIONREQUEST_STATUS_CHANGED("collectionrequest.status.changed");

    private String val;

    private EventsType(String eventType) {
        this.val = eventType;
    }

    @JsonValue
    public String getValue() {
        return this.val;
    }

    public static EventsType fromValue(String value) {
        if (value.equalsIgnoreCase(PAYMENT_STATUS_CHANGED.getValue())) {
            return PAYMENT_STATUS_CHANGED;
        } else if (value.equalsIgnoreCase(COLLECTION_RECEIVED.getValue())) {
            return COLLECTION_RECEIVED;
        } else if (value.equalsIgnoreCase(CONTACT_CREATED.getValue())) {
            return CONTACT_CREATED;
        } else {
            return COLLECTIONREQUEST_STATUS_CHANGED;
        }
    }
}
