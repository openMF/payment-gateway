package org.apache.payment.gateway.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @author Sanyam Goel created on 26/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class TransactionRequest {

    private long vendorId;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private double amount;

    private String currency;

    private String description;

    private String metaData;

    private long organisationId;

}
