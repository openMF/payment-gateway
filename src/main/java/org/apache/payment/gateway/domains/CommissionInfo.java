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
    @Column(name = "id", unique = true) // Todo is this annotation necessaty here?
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vendor_id")
    private int vendorId;

    @Column(name = "commission_type", precision = 4)
    private double commissionType;  // {FLAT/PERCENTAGE}

    @Column(name = "value", precision = 4)
    private double value;

    @Column(name = "currency_type")
    private String currencyType;    // USD, INR

    @Column(name = "range_start", precision = 4)
    private double rangeStart;

    @Column(name = "range_end", precision = 4)
    private double rangeEnd;

    // Todo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

}
