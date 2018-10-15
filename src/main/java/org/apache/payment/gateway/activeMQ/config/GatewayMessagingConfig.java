/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.payment.gateway.activeMQ.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.payment.gateway.activeMQ.service.GatewayEventListener;
import org.apache.payment.gateway.activeMQ.util.PaymentGatewayConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class GatewayMessagingConfig {

    private Environment env;
    private GatewayEventListener gatewayEventListener;
    private String channelName;

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";

    private String requestQueueName;
    private String responseQueueName;
    private CachingConnectionFactory connectionFactory;
    private ActiveMQConnectionFactory amqConnectionFactory;
    private Map<String, MessageProducer> outboundQueues;
    private Session producerSession;

    @Autowired
    public GatewayMessagingConfig(Environment env, GatewayEventListener gatewayEventListener) {
        this.env = env;
        this.gatewayEventListener = gatewayEventListener;
        outboundQueues = new HashMap<>();

        amqConnectionFactory = new ActiveMQConnectionFactory();
        try {
            amqConnectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
            amqConnectionFactory.setUserName("admin");
            amqConnectionFactory.setPassword("admin");
        } catch (Exception e) {
            amqConnectionFactory.setBrokerURL(this.env.getProperty("brokerUrl"));
        }

        connectionFactory = new CachingConnectionFactory(amqConnectionFactory);
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public void connectQueue() {
        setRequestQueueName(this.channelName + "." + PaymentGatewayConstants.CHANNEL_INBOUND_USAGE);
        setResponseQueueName(this.channelName + "." + PaymentGatewayConstants.CHANNEL_OUTBOUND_USAGE);

        // Establish a connection for the producer.
        try {

            // Establish a connection for the consumer.
            // Note: Consumers should not use PooledConnectionFactory.
            final Connection consumerConnection = amqConnectionFactory.createConnection();
            consumerConnection.start();

            // Create a session.
            final Session consumerSession = consumerConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            final Destination consumerDestination = consumerSession.createQueue(getResponseQueueName());

            // Create a message consumer from the session to the queue.
            final MessageConsumer consumer = consumerSession.createConsumer(consumerDestination);
            consumer.setMessageListener(gatewayEventListener);

            // Now create the outbound queue
            createOutboundQueue(getRequestQueueName());

        } catch (Exception E) {

        }

    }

    public void createOutboundQueue(String queueName) {
        try {
            final Connection producerConnection = connectionFactory.createConnection();
            producerConnection.start();

            // Create a session.
            producerSession = producerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            final Destination producerDestination = producerSession.createQueue(queueName);

            // Create a producer from the session to the queue.
            final MessageProducer producer = producerSession.createProducer(producerDestination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            outboundQueues.put(queueName, producer);
        } catch (JMSException E) {

        }
    }

    public MessageProducer getMessageProducer(String queueName) {
        return outboundQueues.get(queueName);
    }

    public Session getOutboundSession() {
        return this.producerSession;
    }

    public String getRequestQueueName() {
        return requestQueueName;
    }

    public void setRequestQueueName(String requestQueueName) {
        this.requestQueueName = requestQueueName;
    }

    public String getResponseQueueName() {
        return responseQueueName;
    }

    public void setResponseQueueName(String responseQueueName) {
        this.responseQueueName = responseQueueName;
    }
}
