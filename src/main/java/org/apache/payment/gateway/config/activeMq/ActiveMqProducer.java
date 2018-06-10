package org.apache.payment.gateway.config.activeMq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.config.activeMq.models.MessageProperties;

import javax.jms.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Rahul Goel created on 10/6/18
 */

public class ActiveMqProducer extends AbstractActiveMqBase{

    private static ActiveMqProducer activeMqProducerInstance = null;

    private static Logger logger = LogManager.getLogger(ActiveMqProducer.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Map<Destination, MessageProducer> producerMap = Collections.synchronizedMap(new HashMap<>());

    ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,
            0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    protected ActiveMqProducer() {
        initProducer();
    }

    private void initProducer(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ActiveMqConfig.getActiveMqConfigInstance();
        ACTIVEMQ_HOST = ActiveMqConfig.ACTIVEMQ_HOST;
        getProducerMap().clear();
        init();
    }

    public synchronized static ActiveMqProducer getInstance() {
        if (activeMqProducerInstance == null) {
            activeMqProducerInstance = new ActiveMqProducer();
        }
        return activeMqProducerInstance;
    }

    public Map<String, Future> publishToTopic(Set<String> topics, MessageProperties message) throws Exception {
    	Map<String, Future> result = new HashMap<>();
    	for (String topic : topics) {
            try {
                result.put(topic, publish(topic, message));
            } catch (JsonProcessingException e) {
                throw e;
            }
        }
    	return result;
    }

    protected void setMessageProperties(Message message, String destination, MessageProperties messageProperties) throws JMSException {
        message.setStringProperty(ActiveMqConfig.MESSAGE_DESTINATION_PROPERTY, destination);
    }


    protected Future publish(String topic, MessageProperties messageProperties) throws Exception {
        if(session!=null) {
            Future fut = executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Destination destination = session.createTopic(topic);
                        MessageProducer producer = getMessageProducer(destination);
                        TextMessage textMessage = session.createTextMessage(objectMapper.writeValueAsString(messageProperties.getMessage()));
                        setMessageProperties(textMessage, topic, messageProperties);
                        producer.send(destination, textMessage);
                    } catch (Exception e) {
                        logger.error("Error occurred while publishing message to - " + topic + " with payload - " + messageProperties, e);
                        throw new RuntimeException(e);
                    }
                }
            });
            return fut;
        }else{
            Exception e  = new Exception("Session is not found for the connection.");
            logger.error("Session was not found for publishing message to - " + topic + " with payload - " + messageProperties, e);
            throw e;
        }
    }

    public Map<Destination, MessageProducer> getProducerMap() {
        return producerMap;
    }

    public void setProducerMap(Map<Destination, MessageProducer> producerMap) {
        this.producerMap = producerMap;
    }

    protected synchronized MessageProducer getMessageProducer(Destination destination) throws JMSException {
        if(getProducerMap().containsKey(destination)){
            return getProducerMap().get(destination);
        }else{
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            if(!getProducerMap().containsKey(destination)) {
                getProducerMap().put(destination, producer);
            }else{
                producer.close();
            }
            return getProducerMap().get(destination);
        }
    }

    @Override
    public void onException(JMSException exception) {
        logger.error("JMSException occurred.", exception);
        initProducer();
    }

    @Override
    protected void createSession(Connection connection) throws Exception{
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }
}