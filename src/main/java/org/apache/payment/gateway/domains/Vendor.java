package org.apache.payment.gateway.domains;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Rahul Goel created on 3/6/18
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendor")
public class Vendor extends BaseEntity {

    @Id
    @Column(name = "vendor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vendorId;

    @Column(name = "vendor_name", length = 63, nullable = false)
    private String vendorName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "vendor_api_key", nullable = false, unique = true) // Todo check Unique
    private String vendorApiKey;

    @Column(name = "vendor_meta")
    private String vendorMeta;

    @Column(name = "callback_endpoint", nullable = false)
    private String callBackEndpoint;

    @Column(name = "vendor_authentication_key", nullable = false)
    private String vendorAuthenticationKey;

    @Column(name = "queue_authentication_key", nullable = false)
    private String queueAuthenticationKey;

    @Column(name = "currency", nullable = false)
    private String currency;    // CurrencyType

//    @Column(name = "county")
//    private String country;     // Todo: [countryName(IND, USA, etc), countryCode(+91, +1, etc)]

//    @Column(name = "country_name")
//    private String countryName;
//
//    @Column(name = "country_code")
//    private String countryCode;


    public Vendor(String vendorName){
        this.vendorName = vendorName;
    }

}
