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

import org.apache.payment.gateway.activeMQ.util.PaymentGatewayConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitGatewayQueues {

    @Autowired
    private GatewayMessagingConfig messagingConfig;

    @Autowired
    private GatewayMessagingConfig gatewayMessagingConfig;


    @PostConstruct
    public void init() {
        // We are going to initialize a single request queue and response queue, not one per payment channel
        gatewayMessagingConfig.setChannelName(PaymentGatewayConstants.ACTIVEMQ_SUBSCRIBER_SERVICE_NAME);
        gatewayMessagingConfig.connectQueue();
    }
}