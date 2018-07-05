package org.apache.payment.gateway.config.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rahul Goel created on 10/6/18
 */

public class ActiveMqConfig {

    public static ActiveMqConfig activeMqConfig;
    private static ActiveMQConnectionFactory connectionFactory;
    private static Logger logger = LogManager.getLogger(ActiveMqConfig.class);

    /*
    * ActiveMq configuration Constants
    * */

    public static final Long ACTIVEMQ_REDELIVERY_POLICY_INITAL_DELAY = 60000L;
    public static final long ACTIVEMQ_REDELIVERY_MAX_REDELIVERY_DELAY = 5000;
    public static final Long ACTIVEMQ_PREFETECH_SIZE = 10L;
    public static final Long ACTIVEMQ_PORT = 8161L;
    public static final String ACTIVEMQ_HOST = "localhost";
    public static final String ACTIVEMQ_SUBSCRIBER_SERVICE_NAME = "payment-gateway";
    public static final String ACTIVEMQ_SUBSCRIBE_TOPICS = "";
    public static final Set<String> ACTIVEMQ_SUBSCRIBE_TOPICS_SET = new HashSet<>(Arrays.asList(ACTIVEMQ_SUBSCRIBE_TOPICS.split("\\s*,\\s*")));
    public static final Boolean ACTIVEMQ_USE_ASYNC_SEND = true;
    public static final String MESSAGE_DESTINATION_PROPERTY = "messageDestination";

    public static final boolean ACTIVEMQ_USE_DEDICATED_TASK_RUNNER = false;
    public static final int ACTIVEMQ_REDELIVERY_MAX_REDELIVERIES = 0;
    public static final String ACTIVEMQ_SCHEDULER_HOST = "";
    public static final String ACTIVEMQ_SUBSCRIBER_HOST = "";


    private ActiveMqConfig(){
        logger.info("ActiveMQ Host is " + ACTIVEMQ_HOST);
        logger.info("ActiveMQ Port is " + ACTIVEMQ_PORT);
        logger.info("ActiveMQ Service Name is " + ACTIVEMQ_SUBSCRIBER_SERVICE_NAME);
        logger.info("ActiveMQ Subscribe Topics is " + ACTIVEMQ_SUBSCRIBE_TOPICS);
    }

    public static synchronized ActiveMQConnectionFactory getConnectionFactory(){
        if(connectionFactory == null){
            ActiveMqConfig.getActiveMqConfigInstance();
            ActiveMQConnectionFactory activeMQConnectionFactory =
                    new ActiveMQConnectionFactory("tcp://" + ActiveMqConfig.ACTIVEMQ_HOST + ":" + ActiveMqConfig.ACTIVEMQ_PORT + "?jms.prefetchPolicy.all=" + ActiveMqConfig.ACTIVEMQ_PREFETECH_SIZE);
            activeMQConnectionFactory.setUseDedicatedTaskRunner(ActiveMqConfig.ACTIVEMQ_USE_DEDICATED_TASK_RUNNER);
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            redeliveryPolicy.setInitialRedeliveryDelay(ActiveMqConfig.ACTIVEMQ_REDELIVERY_POLICY_INITAL_DELAY);
            redeliveryPolicy.setMaximumRedeliveryDelay(ActiveMqConfig.ACTIVEMQ_REDELIVERY_MAX_REDELIVERY_DELAY);
            redeliveryPolicy.setMaximumRedeliveries(ActiveMqConfig.ACTIVEMQ_REDELIVERY_MAX_REDELIVERIES);
            activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
            activeMQConnectionFactory.setUseAsyncSend(ACTIVEMQ_USE_ASYNC_SEND);
            connectionFactory = activeMQConnectionFactory;
        }
        logger.info("initiating connection to " + ActiveMqConfig.ACTIVEMQ_HOST + ":" + ActiveMqConfig.ACTIVEMQ_PORT);
        return connectionFactory;
    }

    public synchronized static ActiveMqConfig getActiveMqConfigInstance() {
        if (activeMqConfig == null) {
            activeMqConfig = new ActiveMqConfig();
        }
        return activeMqConfig;
    }
}
