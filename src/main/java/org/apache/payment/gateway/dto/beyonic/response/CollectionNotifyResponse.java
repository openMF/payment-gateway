package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.payment.gateway.dto.beyonic.MetaData;

/**
 * Fineract -> money to receive from mobile subscriber
 * Collection Request allow you to notify a customer to send funds to you
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollectionNotifyResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("organisation")
    private String organisation;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("phonenumber")
    private String phoneNumber;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("metadata")
    private MetaData metaData;  //todo

    @JsonProperty("success_message")
    private String successMessage;

    @JsonProperty("send_instructions")
    private boolean sendInstructions;   // Todo to set -> default - true

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("expiry_date")
    private String expiryDate;  //opt  //Defaults to “24 hours”. Specifies the date and time when this collection request will be marked as expired. Examples of valid values for this field include strings such as “tomorrow”, “24 hours”, “2 minutes”, or %d/%m/%Y format e.g 24/05/2019 or %d/%m/%Y %H:%M:%S format e.g 24/05/2019 13:24:12

//    @JsonProperty("contact")
//    private ContactInfo contactDetails;    //todo check with mentors

    @JsonProperty("created")
    private String createdDate; // YYYY-MM-DDTHH:MM:SSZ UTC format

    @JsonProperty("author")
    private long authorId;

    @JsonProperty("modified")
    private String modifiedDate;

    @JsonProperty("updated_by")
    private String updatedByUserId;

    @JsonProperty("collection")
    private long collectionId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("error_message")
    private String errorMessage;

    @JsonProperty("error_details")
    private String errorDetails;

}
