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

    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_phone_number")
    private String clientPhoneNumber;

    @Column(name = "client_account_number")
    private String clientAccountNumber;

    @Column(name = "client_email_id", nullable = true)
    private String clientEmaidID;   // Todo: Nullable

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "transaction_amount", precision = 4)
    private double transactionAmount;

    @Column(name = "currency_type")
    private String currencyType;

    @Column(name = "transaction_description")
    private String transactionDescription;

    @Column(name = "transaction_mode")
    private String transactionMode; // Internet Banking, Card

    @Column(name = "vendor_meta_data")
    private String vendorMetaData;

    @Column(name = "transaction_fee", precision = 4)
    private double transactionFee;

    @Column(name = "transaction_total_tax", precision = 4)
    private double transactionTotalTax;

    @Column(name = "external_reference_id")
    private String externalReferenceId; // Todo what is external Reference ID ?

    @Column(name = "status")
    private String status;  // INITIATED, PROCESSED, REFUNDED, FAILED

    @Column(name = "error_info_id")
    private int errorInfoId;

    @Column(name = "transaction_type")
    private String transactionType; // INBOUND/OUTBOUND

}
