package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private long id;

    private long organisation;

    private double amount;  //man

    private String currency;    //man

    private String phonenumber; //man

    private String reason;      //opt

    private MetaData metadata;   //opt   //todo

    private String successMessage;  //opt

    private boolean sendInstructions;   //opt

    private String instructions;    //opt

    private String expiryDate;  //opt

    //private ContactInfo contact;    //todo

    private long organization;

    private String created;

    private long author;

    private String modified;

    private String updatedBy;

    private long collection;

    private String status;

    private String errorMessage;

    private String errorDetails;

}
