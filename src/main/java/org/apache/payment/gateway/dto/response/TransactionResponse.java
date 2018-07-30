package org.apache.payment.gateway.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.payment.gateway.constants.TransactionStatus;

/**
 * @author Sanyam Goel created on 29/7/18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class TransactionResponse {

    private long clientId;

    private long vendorId;

    private String phoneNumber;

    private double amount;

    private String currency;

    private String firstName;

    private String lastName;

    private TransactionStatus paymentStatus;

    private String referenceId; //transaction reference ID by vendor(unique for a particular transaction)
}
