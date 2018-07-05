package org.apache.payment.gateway.config.activeMq;

import org.apache.payment.gateway.config.activeMq.listener.ActiveMqMessageListener;
import org.apache.payment.gateway.config.activeMq.models.MessageProperties;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author Rahul Goel created on 10/6/18
 */


public abstract class ActiveMqBaseUtil {

    public void getMessage(Set<String> topics, ActiveMqMessageListener activeMqMessageListener) {
        ActiveMqConsumer activeMqConsumer = ActiveMqConsumer.getInstance();
        activeMqConsumer.addListeners(topics, activeMqMessageListener);
    }


    public Map<String, Future> publishToTopic(Set<String> topics, MessageProperties message) throws Exception {
        return ActiveMqProducer.getInstance().publishToTopic(topics, message);
    }
}
