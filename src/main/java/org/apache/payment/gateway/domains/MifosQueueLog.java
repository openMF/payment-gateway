package org.apache.payment.gateway.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Sanyam Goel created on 10/6/18
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MifosQueueLog extends BaseEntity {

//    mifos_queue_log(id, vendor_id, transaction_id, external_reference_id,request_object)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vendorId;

    private int transactionId;

    private String externalReferenceId;

    private String requestObject;
}
