package org.apache.payment.gateway.dto.beyonic;

import java.util.ArrayList;

public class PaymentObject {

    private long id;

    private long organization;

    private String payment_type;

    private double amount;

    private String currency;

    private long account;

    private String description;

    private ArrayList<String> phone_nos;

    private String start_date;

    //private MetaData metadata;

    private String state;

    private String last_error;

    private String rejected_reason;

    private String rejected_time;

    private long rejected_by;

    private String cancelled_reason;

    private String cancelled_time;

    private long cancelled_by;

    private String created;

    private long author;

    private String modified;

    private String updated_by;

    private String remote_transaction_id;

    private boolean send_sms_message;
}
