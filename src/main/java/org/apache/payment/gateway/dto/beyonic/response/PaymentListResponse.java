package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * @author Sanyam Goel created on 23/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentListResponse {

    @JsonProperty(value = "count")
    private long count;

    @JsonProperty(value = "next")
    private String next;

    @JsonProperty(value = "previous")
    private String previous;

    @JsonProperty(value = "results")
    private List<PaymentResponse> results;

}
