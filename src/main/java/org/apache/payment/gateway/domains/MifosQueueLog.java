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
    private int id;

    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "transaction_id")
    private int transactionId;

    @Column(name = "external_reference_id")
    private String externalReferenceId;

    @Column(name = "request_object")
    private String requestObject;

}
