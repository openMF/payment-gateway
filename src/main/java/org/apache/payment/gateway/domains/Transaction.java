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
@Table(name = "transaction", indexes = {
        @Index(columnList = "vendor_id", name = "vendor_id"),
        @Index(columnList = "vendor_reference_id", name = "vendor_reference_id")
})
public class Transaction extends BaseEntity {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @Column(name = "vendor_id", insertable = false, updatable = false, nullable = false)
    private long vendorId;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_phone_number", nullable = false)
    private String clientPhoneNumber;

    @Column(name = "client_account_number", nullable = false)
    private String clientAccountNumber;

    @Column(name = "client_email_id", nullable = true)
    private String clientEmailID;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "transaction_amount", precision = 4, nullable = false)
    private double transactionAmount;

    @Column(name = "currency_type", nullable = false)
    private String currencyType;

    @Column(name = "transaction_description")
    private String transactionDescription;

    @Column(name = "transaction_mode", nullable = false)
    private String transactionMode; // Internet Banking, Card

    @Column(name = "vendor_meta_data", nullable = false)
    private String vendorMetaData;

    @Column(name = "transaction_fee", precision = 4, nullable = false)
    private double transactionFee;

    @Column(name = "transaction_total_tax", precision = 4, nullable = false)
    private double transactionTotalTax;

    @Column(name = "vendor_reference_id", nullable = false) // todo unique?
    private String vendorReferenceId; // Todo what is external Reference ID ?

    @Column(name = "status", nullable = false)
    private String status;  // INITIATED, PROCESSED, REFUNDED, FAILED

//    @Column(name = "error_info_id", insertable = false, updatable = false, nullable = false)
//    private long errorInfoId;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType; // INBOUND/OUTBOUND


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    private Vendor vendor;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "error_info_id", referencedColumnName = "error_info_id", nullable = false)
//    private ErrorInfo errorInfo;

}
