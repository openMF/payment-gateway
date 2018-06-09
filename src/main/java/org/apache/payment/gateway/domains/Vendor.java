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
public class Vendor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vendor_name", length = 63)
    private String vendorName;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "vendor_api_key")
    private String vendorApiKey;

    @Column(name = "vendor_meta")
    private String vendorMeta;

    @Column(name = "callback_endpoint")
    private String callBackEndpoint;

    @Column(name = "vendor_authentication_key")
    private String vendorAuthenticationKey;

    @Column(name = "queue_authentication_key")
    private String queueAuthenticationKey;

    @Column(name = "currency")
    private String currency;    // CurrencyType

//    @Column(name = "county")
//    private String country;     // Todo: [countryName(IND, USA, etc), countryCode(+91, +1, etc)]


    public Vendor(String vendorName){
        this.vendorName = vendorName;
    }

}
