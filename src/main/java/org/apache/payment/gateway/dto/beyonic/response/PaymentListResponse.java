package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private long count;

    private String next;

    private String previous;

    private List<PaymentResponse> results;

    public List<PaymentResponse> getResults() {
        return results;
    }
}
