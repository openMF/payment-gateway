package org.apache.payment.gateway.dto.beyonic.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.payment.gateway.dto.beyonic.MetaData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private long id;

    private long organization;

    private String amount;

    private String currency;

    private String account;

    private String paymentType;

    private MetaData metadata;

    private String description;

    private List<String> phoneNos;

    private String state;

    private Object lastError;

    private Object rejectedReason;

    private Object rejectedBy;

    private Object rejectedTime;

    private Object cancelledReason;

    private Object cancelledBy;

    private Object cancelledTime;

    private String created;

    private long author;

    private String modified;

    private Object updatedBy;

    private String startDate;

    private Object remoteTransactionId;
}
