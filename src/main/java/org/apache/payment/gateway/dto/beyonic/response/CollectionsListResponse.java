package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanyam Goel created on 23/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollectionsListResponse {

    @JsonProperty(value = "count")
    private long count;

    @JsonProperty(value = "next")
    private String next;

    @JsonProperty(value = "previous")
    private String previous;

    @JsonProperty(value = "results")
    private List<CollectionsResponse> collectionNotifyResponses;

}
