package org.apache.payment.gateway.config.activeMq;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.listener.ActiveMqMessageListener;
import org.apache.payment.gateway.config.activeMq.models.PublishBody;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Rahul Goel created on 10/6/18
 */

public class ActiveMqConsumer extends AbstractActiveMqBase implements MessageListener {

    private static ActiveMqConsumer activeMqConsumerInstance = null;
    private volatile HashMap<String, List<ActiveMqMessageListener>> listenersMap = new HashMap<>();
    private static Logger logger = LogManager.getLogger(ActiveMqConsumer.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private ActiveMqConsumer() {
        initConsumer();
    }

    private void initConsumer(){
        logger.info("ActiveMq initiating consumer");
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ActiveMqConfig.getActiveMqConfigInstance();
        ACTIVEMQ_HOST = ActiveMqConfig.ACTIVEMQ_SUBSCRIBER_HOST;
        init();
        listenersMap.clear();
        subscribe(ActiveMqConfig.ACTIVEMQ_SUBSCRIBE_TOPICS);
    }

    public synchronized static ActiveMqConsumer getInstance() {

        if (activeMqConsumerInstance == null) {
            activeMqConsumerInstance = new ActiveMqConsumer();
        }
        return activeMqConsumerInstance;
    }

    public void addListeners(Set<String> topics, ActiveMqMessageListener activeMqMessageListener){
        for (String topic : topics) {
            String destination = "queue://Consumer." + ActiveMqConfig.ACTIVEMQ_SUBSCRIBER_SERVICE_NAME + topic;

            listenersMap.putIfAbsent(destination, new ArrayList<ActiveMqMessageListener>());

            listenersMap.get(destination).add(activeMqMessageListener);
        }
    }

    public void subscribe(Set<String> topics) {
        for (String topic : topics) {
            logger.info("ActiveMq subscribing to "+topic);
            subscribe(topic);
        }
    }

    public void subscribe(String topic) {
        if(session!=null) {
            try {
                Destination destination = this.session.createQueue("Consumer." + ActiveMqConfig.ACTIVEMQ_SUBSCRIBER_SERVICE_NAME + topic);
                MessageConsumer consumer = this.session.createConsumer(destination);
                consumer.setMessageListener(this);
                logger.info("ActiveMq subscribed to " + topic);
            } catch (JMSException e) {
                logger.error("Error occurred while creating consumer",e);
                throw new RuntimeException(e);
            }
        }else{
            throw new RuntimeException("Session is not created for consumption");
        }
    }

    @Override
    public void onException(JMSException exception) {
        logger.info("ActiveMq consumer exception - "+exception.toString());
        initConsumer();
    }

    @Override
    protected void createSession(Connection connection) throws Exception{
        session = connection.createSession(true, ActiveMQSession.SESSION_TRANSACTED);
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage txtMsg = (TextMessage) message;
            String dest = message.getJMSDestination().toString();
            if (listenersMap.size() == 0) {
                session.rollback();
                return;
            }
            try {
            	logger.info("ActiveMq message received on topic "+ dest + " with id - " + txtMsg.getJMSMessageID() + " on connection - " + this.connection.getClientID());
                notifyListenersMessage(txtMsg, dest, () -> {
                    try {
                        session.commit();
                    } catch (JMSException e) {
                        logger.error("Exception occurred while committing the message", e);
                    }
                });
            }catch (Exception e){
                logger.error("Exception occurred while processing the message.", e);
                session.rollback();
            }
        } catch (JMSException e) {
            logger.error("Error occurred while rollback.", e);
        }
    }

    private void notifyListenersMessage(TextMessage message, String dest, MessageAcknowledgement messageAcknowledgement) throws Exception {
        if (listenersMap.get(dest) != null) {
        	String messageText = message.getText();
            for (ActiveMqMessageListener activeMqMessageListener : listenersMap.get(dest)) {
                try {
                    PublishBody publishBody = objectMapper.readValue(messageText, PublishBody.class);
                    activeMqMessageListener.onMessage(publishBody, dest, messageAcknowledgement);
                } catch (Exception e){
                    throw e;
                }
            }
        }
    }
}