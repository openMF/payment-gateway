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
@Table(name = "error_info", indexes = {
        @Index(columnList = "vendor_id", name = "vendor_id")
})
public class ErrorInfo extends BaseEntity {

    @Id
    @Column(name = "error_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long errorInfoId;

    @Column(name = "vendor_id", insertable = false, updatable = false, nullable = false)
    private long vendorId;

    @Column(name = "error_code", nullable = false)
    private String errorCode;

    @Column(name = "error_description")
    private String errorDescription;

    @Column(name = "http_status_code", nullable = false)
    private String httpStatusCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    private Vendor vendor;

}
