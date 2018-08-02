package org.apache.payment.gateway.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.payment.gateway.dto.beyonic.MetaData;

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

    @JsonProperty(required = true)
    private long clientId;

    @JsonProperty(required = true)
    private long vendorId;

    @JsonProperty(required = true)
    private String phoneNumber;

    @JsonProperty(required = false)
    private String firstName;

    @JsonProperty(required = false)
    private String lastName;

    @JsonProperty(required = true)
    private double amount;

    @JsonProperty(required = true)
    private String currency;

    @JsonProperty(required = false)
    private String description;

    @JsonProperty(required = false)
    private MetaData metaData;

    @JsonProperty(required = true)
    private long organisationId;

    @JsonProperty(required = false)
    private String reason;

}
