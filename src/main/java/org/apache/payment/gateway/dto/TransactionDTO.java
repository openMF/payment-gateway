package org.apache.payment.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sanyam Goel created on 16/6/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private long transactionId;
    private long vendorId;
    private long createdAt;
    private long updatedAt;
    private String clientName;
    private String clientPhoneNumber;
    private String clientAccountNumber;
    private String clientEmailID;
    private String accountType;
    private String currencyType;
    private String transactionDescription;
    private String transactionMode;
    private String vendorMetaData;
    private String vendorReferenceId;
    private String status;
    private String transactionType;
    private double transactionAmount;
    private double transactionFee;
    private double transactionTotalTax;

}
