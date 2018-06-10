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
@Table(name = "commission_info", indexes = {
        @Index(columnList = "vendor_id", name = "vendor_id")
})
public class CommissionInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vendor_id", insertable = false, updatable = false, nullable = false)
    private long vendorId;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    private Vendor vendor;

    // select * from vendor inner join commission_info on commission_info.vendor_id = vendor.vendor_id;

}
