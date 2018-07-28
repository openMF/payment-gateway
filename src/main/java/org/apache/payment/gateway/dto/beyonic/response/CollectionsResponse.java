package org.apache.payment.gateway.dto.beyonic.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Beyonic uses the term “Collections” for payments you receive (or collect) from mobile subscribers
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollectionsResponse {

    @JsonProperty("id")
    private long id;

    @JsonProperty("remote_transaction_id")
    private String remoteTransactionId;

    @JsonProperty("organization")
    private long organizationId;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("phonenumber")
    private String phoneNumber;

    @JsonProperty("payment_date")
    private String paymentDate;

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created")
    private String createdDate;

    @JsonProperty("author")
    private long author;

    @JsonProperty("modified")
    private String modifiedDate;

    @JsonProperty("updated_by")
    private String updatedByUserId;

    @JsonProperty("collection_request")
    private String collectionRequest;  // CollectionNotifyResponse // JSON String // JSON parser to class from string

}
