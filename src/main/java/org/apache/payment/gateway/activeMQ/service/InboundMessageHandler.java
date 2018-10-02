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

import com.oracle.javafx.jmx.json.JSONException;
import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.dto.beyonic.request.PaymentRequest;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.apache.payment.gateway.repository.VendorRepository;
import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.service.beyonic.PaymentService;
import org.apache.payment.gateway.dto.beyonic.request.PaymentRequest;

@Component
public class InboundMessageHandler {
    // TODO: handle proper logging

    class Message {

        @SerializedName("transactionType")
        private String transactionType;

        @SerializedName("paymentChannelId")
        private String paymentChannelId;

        @SerializedName("transactionReference")
        private String transactionReference;

        @SerializedName("paymentAccount")
        private String paymentAccount;

        @SerializedName("transactionAmount")
        private String transactionAmount;

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public long getPaymentChannelId() {
            return Long.parseLong(paymentChannelId);
        }

    }

    private static final String transactionTypeParameterName = "transactionType";
    private static final String amountParameterName = "amount";
    private static final String channelNameParameterName = "channelName";
    private static final String externalRefIdParameterName = "externalRefId";
    private static final String paymentAccountTypeParameterName = "accountType";
    private static final String paymentAccountParameterName = "paymentAccount";
    private static final String channelMessageParameterName = "message";
    private static final String paymentNoteParameterName = "paymentNote";
    private static final String mobileNoParameterName = "mobileNo";
    private static final String destAccountParameterName = "destAccount";
    private static final String localeParameterName = "locale";
    protected static final Logger logger = Logger.getLogger(InboundMessageHandler.class.getName());

    public static final String TRANSACTION_TYPE_LOAN_DISBURSAL = "loanDisbursal";

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    public InboundMessageHandler() {

    }

    public void handlePayment(String message) {
        logger.info("inbound_payment_start " + message.toString());

        Gson gson = new Gson();
        Message msgObj = gson.fromJson(message, Message.class);
        String transactionType = msgObj.getTransactionType();

        // Look up the vendor Name from the vendor ID
        Vendor vendor = vendorRepository.getVendorById(msgObj.getPaymentChannelId());

        switch (vendor.getVendorName().toLowerCase()) {
            case "beyonic":
                if (transactionType.equals(TRANSACTION_TYPE_LOAN_DISBURSAL)) {
                    PaymentRequest paymentRequest = new PaymentRequest();
                    paymentRequest.setPhonenumber(msgObj.paymentAccount);
                    paymentRequest.setAmount(Float.parseFloat(msgObj.transactionAmount));
                    paymentRequest.setCurrency(vendor.getCurrency());
                    paymentRequest.setPayment_type("money");

                    //  Get the payer account from the vendor config
                    String vendorMeta = vendor.getVendorMeta();
                    JSONParser parser = new JSONParser();
                    try {
                        JSONObject json = (JSONObject) parser.parse(vendorMeta);
                        String account = (String) json.get("account").toString();
                        paymentRequest.setAccount(Long.parseLong(account));
                    } catch (ParseException E) {

                    }
                    paymentRequest.setDescription(msgObj.transactionReference);
                    paymentService.sendPayment(paymentRequest, vendor.getVendorApiKey());
                }
                break;
        }

    }


}
