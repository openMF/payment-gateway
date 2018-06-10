package org.apache.payment.gateway.config.activeMq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.listener.AbstractMessageListener;
import org.apache.payment.gateway.config.activeMq.models.ActionType;
import org.apache.payment.gateway.config.activeMq.models.MessageProperties;
import org.apache.payment.gateway.config.activeMq.models.PublishBody;
import org.apache.payment.gateway.config.activeMq.models.QueueMessageModel;
import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.utils.Utility;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author Rahul Goel created on 10/6/18
 */

@Component
public class ActiveMqUtil extends ActiveMqBaseUtil{

    private static Logger logger = LogManager.getLogger(ActiveMqConsumer.class);

    @Async
    @Loggable
    public Future publishMessageToTopic(Set<String> topics, ActionType actionType, Object payload) throws Exception {
        QueueMessageModel queueMessageModel = new QueueMessageModel(payload, actionType, payload.getClass().getName());
        PublishBody publishBody = new PublishBody(queueMessageModel.getEntity(), Utility.objectToJson(queueMessageModel));
        MessageProperties messageProperties = new MessageProperties(publishBody);
        Map result = null;
        try {
            result = super.publishToTopic(topics, messageProperties);
        } catch (Exception var9) {
            logger.error("[publishMessageToTopic] : PUBLISH_MESSAGE_TO_TOPIC" + topics
                    + " : actionType : " + actionType + " : publishBody : " + publishBody);
            throw var9;
        }
        return new AsyncResult(result);
    }

    @Async
    @Loggable
    public Future publishMessageToTopic(Set<String> topics, ActionType actionType, Object payload, String requestId) throws Exception {
        QueueMessageModel queueMessageModel = new QueueMessageModel(payload, actionType, payload.getClass().getName(), requestId);
        PublishBody publishBody = new PublishBody(queueMessageModel.getEntity(), Utility.objectToJson(queueMessageModel));
        MessageProperties messageProperties = new MessageProperties(publishBody);
        Map result = null;
        try {
            result = this.publishToTopic(topics, messageProperties);
        } catch (Exception var10) {
            logger.error("[publishMessageToTopic] : PUBLISH_MESSAGE_TO_TOPIC" + topics
                    + " : actionType : " + actionType + " : publishBody : " + publishBody);
            throw var10;
        }
        return new AsyncResult(result);
    }

    public void subscribe(Set<String> topics, AbstractMessageListener abstractMessageListener) {
        super.getMessage(topics, abstractMessageListener);
    }
}
