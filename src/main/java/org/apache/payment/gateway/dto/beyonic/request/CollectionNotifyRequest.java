package org.apache.payment.gateway.dto.beyonic.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.payment.gateway.dto.beyonic.MetaData;

/**
 * @author Sanyam Goel created on 23/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollectionNotifyRequest {

    @JsonProperty(required = true, value = "phonenumber")
    private String phoneNumber; //req

    @JsonProperty(required = false, value = "first_name")
    private String firstName;

    @JsonProperty(required = false, value = "last_name")
    private String lastName;

    @JsonProperty(required = true, value = "amount")
    private Double amount;      //req

    @JsonProperty(required = true, value = "currency")
    private String currency;    //req

    @JsonProperty(required = false, value = "reason")
    private String reason;

    @JsonProperty(required = false, value = "metadata")
    private MetaData metaData;    // JSON-String    // parse to MetaData class

    @JsonProperty(required = false, value = "success_message")
    private String successMessage;

    @JsonProperty(required = true, defaultValue = "true", value = "send_instructions")
    private boolean sendInstructions;

    @JsonProperty(required = false, value = "instructions")
    private String instructions;

    @JsonProperty(required = false, value = "expiry_date")
    private String expiryDate;  //Date String
}
