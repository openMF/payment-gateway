package org.apache.payment.gateway.dto.beyonic.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private long id;

    private String remote_transaction_id;

    private long organization;

    private double amount;

    private String currency;

    private String phonenumber;

    private String payment_date;

    private String reference;

    private String status;

    private String created;

    private long author;

    private String modified;

    private String updated_by;

    private String collection_request;  // CollectionNotifyResponse
}
