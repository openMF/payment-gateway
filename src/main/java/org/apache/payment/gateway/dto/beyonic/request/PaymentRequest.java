package org.apache.payment.gateway.dto.beyonic.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.payment.gateway.dto.beyonic.MetaData;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    private String phoneNumber;

    private String payment_type;

    private double amount;

    private String currency;

    private long account;

    private String description;

    private String callback_url;

    private MetaData metadata;

    private String firstName;

    private String lastName;

}
