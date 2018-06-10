package org.apache.payment.gateway.config.activeMq.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rahul Goel created on 10/6/18
 */

@Data
@AllArgsConstructor
public class MessageProperties {
    private long delay;
    private long period;
    private int repeat;
    private PublishBody message;

    public MessageProperties(PublishBody message) {
        this.message = message;
    }
}
