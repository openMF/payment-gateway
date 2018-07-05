package org.apache.payment.gateway.config.activeMq.listener;

import org.apache.payment.gateway.config.activeMq.MessageAcknowledgement;
import org.apache.payment.gateway.config.activeMq.models.PublishBody;

/**
 * @author Rahul Goel created on 10/6/18
 */

public interface ActiveMqMessageListener {
    public void onMessage(PublishBody message, String topic, MessageAcknowledgement messageAcknowledgement) throws Exception;
}
