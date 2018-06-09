package org.apache.payment.gateway.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Sanyam Goel created on 9/6/18
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vendorId;

    private String clientName;

    private String clientPhoneNumber;

    private String clientAccountNumber;

    private String clientEmaidID;   // Todo: Nullable

    private String accountType;

    @Column(precision = 4)
    private double transactionAmount;

    private String currencyType;

    private String transactionDescription;

    private String transactionMode; // Internet Banking, Card

    private String vendorMetaData;

    @Column(precision = 4)
    private double transactionFee;

    @Column(precision = 4)
    private double transactionTotalTax;

    private String externalReferenceId; // Todo what is external Reference ID ?

    private String status;  // INITIATED, PROCESSED, REFUNDED, FAILED

    private int errorInfoId;

    private String transactionType; // INBOUND/OUTBOUND


}
