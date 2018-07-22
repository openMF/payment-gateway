package org.apache.payment.gateway.dto.beyonic.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.payment.gateway.dto.beyonic.MetaData;

/**
 * Collection Domain for Beyonic
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRequest {

    private long id;

    private String phonenumber; //man

    private double amount;  //man

    private String currency;    //man

    private String firstName;   //opt

    private String lastName;    //opt

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
