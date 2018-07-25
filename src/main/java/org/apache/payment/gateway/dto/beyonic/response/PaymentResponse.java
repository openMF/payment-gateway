package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.payment.gateway.dto.beyonic.MetaData;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PaymentResponse {  //24

    private long id;

    private String organization;

    private String payment_type;

    private String amount;

    private String currency;

    private String account;

    private String description;

    private ArrayList<String> phoneNos;

    private String start_date;

    private MetaData metadata;  //Todo

    private String state;

    private String last_error;

    private String rejected_reason;

    private String rejected_time;

    private long rejected_by;   // id of user who rejected the payment

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
