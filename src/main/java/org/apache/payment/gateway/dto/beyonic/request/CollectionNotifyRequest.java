package org.apache.payment.gateway.dto.beyonic.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Sanyam Goel created on 23/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollectionNotifyRequest {

    @JsonProperty(required = true)
    private String phoneNumber; //req

    private String firstName;

    private String lastName;

    private Double amount;      //req

    private String currency;    //req

    private String reason;

    private String metaData;    // JSON-String    // parse to MetaData class

    private String successMessage;

    private boolean sendInstructions;

    private String instructions;

    private String expiryDate;  //Date String
}
