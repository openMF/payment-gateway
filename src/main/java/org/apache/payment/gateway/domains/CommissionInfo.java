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
public class CommissionInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int vendorId;

    @Column(precision = 4)
    private double commissionType;

    @Column(precision = 4)
    private double value;

    private String currencyType;    // USD, INR

    @Column(precision = 4)
    private double rangeStart;

    @Column(precision = 4)
    private double rangeEnd;

}
