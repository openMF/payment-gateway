package org.apache.payment.gateway.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Sanyam Goel created on 10/6/18
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mifos_queue_log", indexes = {
        @Index(columnList = "vendor_id", name = "vendor_id"),
        @Index(columnList = "vendor_reference_id", name = "vendor_reference_id")
})
public class MifosQueueLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vendor_id", insertable = false, updatable = false, nullable = false)
    private long vendorId;

    @Column(name = "transaction_id", insertable = false, updatable = false, nullable = false, unique = true)
    private long transactionId;

    @Column(name = "vendor_reference_id", nullable = false)
    private String vendorReferenceId;         // vendor_reference_id -> sent by vendor

    @Column(name = "request_object", nullable = false)
    private String requestObject;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    private Vendor vendor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id", nullable = false)
    private TransactionData transactionData;
}
