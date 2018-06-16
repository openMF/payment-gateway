package org.apache.payment.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sanyam Goel created on 16/6/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendorDTO {

    private long vendorId;
    private String vendorName;
    private boolean isActive;
    private String vendorApiKey;
    private String vendorMeta;
    private String callBackEndpoint;
    private String vendorAuthenticationKey;
    private String queueAuthenticationKey;
    private String currency;    // CurrencyType
    private long createdAt;
    private long updatedAt;

}
