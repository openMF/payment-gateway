package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.payment.gateway.dto.beyonic.Hook;

/**
 * @author Sanyam Goel created on 29/7/18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebHookPaymentStatusResponse {


    @JsonProperty("hook")
    private Hook hook;

    @JsonProperty("data")
    private PaymentResponse data;
}
