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

    @Column(length = 63)
    private String vendorName;

    private boolean isActive;

    private String vendorApiKey;

    private String vendorMeta;

    private String callBackEndpoint;

    private String vendorAuthenticationKey;

    private String queueAuthenticationKey;

    private String currency;

//
//    public Vendor(String vendorName){
//        this.vendorName = vendorName;
//    }

}
