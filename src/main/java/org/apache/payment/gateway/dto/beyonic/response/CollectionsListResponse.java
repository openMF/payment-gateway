package org.apache.payment.gateway.dto.beyonic.response;

import lombok.*;

import java.util.ArrayList;

/**
 * @author Sanyam Goel created on 23/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollectionsListResponse {

    private long count;

    private String next;

    private String previous;

    private ArrayList<CollectionsResponse> collectionNotifyResponses;

}
