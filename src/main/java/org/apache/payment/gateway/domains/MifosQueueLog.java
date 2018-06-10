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
public class MifosQueueLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vendor_id", insertable = false, updatable = false, nullable = false)
    private long vendorId;

    @Column(name = "transaction_id")
    private long transactionId;

    @Column(name = "external_reference_id")
    private String externalReferenceId;

    @Column(name = "request_object")
    private String requestObject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    private Vendor vendor;

}
