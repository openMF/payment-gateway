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
package org.apache.payment.gateway.activeMQ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class GatewayEventListener implements MessageListener {

    private final InboundMessageHandler messageHandler;

    @Autowired
    public GatewayEventListener(InboundMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof ActiveMQTextMessage) {
            try {
                long length = ((ActiveMQTextMessage) message).getSize();
                byte[] content = new byte[(int) length];
                String s = ((ActiveMQTextMessage) message).getText();
                //String s = new String(content);
                messageHandler.handlePayment(s);
                message.acknowledge();
            } catch (JMSException E) {
                throw new RuntimeException(E);
            } catch (RuntimeException E) {
                if (E.getLocalizedMessage() == null) {
                    return;
                }
            }

        } else {
            throw new IllegalArgumentException("Message Error");
        }
    }
}
