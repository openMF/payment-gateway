package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.payment.gateway.dto.beyonic.MetaData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class PaymentResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("organization")
    private String organizationId;

    @JsonProperty("payment_type")
    private String paymentType; // money or airtime

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("account")
    private String accountId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("phone_nos")
    private List<String> phoneNos;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("metadata")
    private MetaData metaData;  //Todo

    @JsonProperty("state")
    private String state;

    @JsonProperty("last_error")
    private String lastError;

    @JsonProperty("rejected_reason")
    private String rejectedReason;

    @JsonProperty("rejected_time")
    private String rejectedTime;

    @JsonProperty("rejected_by")
    private long rejectedByUserId;   // id of user who rejected the payment

    @JsonProperty("cancelled_reason")
    private String cancelledReason;

    @JsonProperty("cancelled_time")
    private String cancelledTime;

    @JsonProperty("cancelled_by")
    private long cancelledByUserId;

    @JsonProperty("created")
    private String created;

    @JsonProperty("author")
    private long authorId;

    @JsonProperty("modified")
    private String lastModifiedTime;

    @JsonProperty("updated_by")
    private String updatedByUserId;

    @JsonProperty("remote_transaction_id")
    private String remoteTransactionId;

    @JsonProperty("send_sms_message")
    private boolean sendSmsMessage;

}
