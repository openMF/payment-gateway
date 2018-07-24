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

    private String success_message;  //opt

    private boolean send_instructions;   //opt

    private String instructions;    //opt

    private String expiry_date;  //opt  //Defaults to “24 hours”. Specifies the date and time when this collection request will be marked as expired. Examples of valid values for this field include strings such as “tomorrow”, “24 hours”, “2 minutes”, or %d/%m/%Y format e.g 24/05/2019 or %d/%m/%Y %H:%M:%S format e.g 24/05/2019 13:24:12

    //private ContactInfo contact;    //todo check with mentors

    private long organization;

    private String created; // YYYY-MM-DDTHH:MM:SSZ UTC format

    private long author;

    private String modified;

    private String updated_by;

    private long collection;

    private String status;

    private String error_message;

    private String error_details;

}
