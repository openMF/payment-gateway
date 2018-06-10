package org.apache.payment.gateway.config.activeMq.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Rahul Goel created on 10/6/18
 */

@Data
public class QueueMessageModel<T> {

    private T payload;
    private ActionType action;
    private String entity;
    private String requestId;

    public QueueMessageModel(T payload, ActionType action, String entity) {
        this.payload = payload;
        this.action = action;
        this.entity = entity;
    }

    public QueueMessageModel(T payload, ActionType action, String entity, String requestId) {
        this.payload = payload;
        this.action = action;
        this.entity = entity;
        this.requestId = requestId;
    }

    public QueueMessageModel() {
    }
}
