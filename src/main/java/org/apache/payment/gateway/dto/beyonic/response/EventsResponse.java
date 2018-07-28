package org.apache.payment.gateway.dto.beyonic.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Sanyam Goel created on 28/7/18
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventsResponse {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "organization")
    private long organisationId;

    @JsonProperty(value = "type")
    private String eventType;

    @JsonProperty(value = "data")
    private Object objectData;

    @JsonProperty(value = "created")
    private String created;

    @JsonProperty(value = "author")
    private long authorId;

    @JsonProperty(value = "modified")
    private String modified;

    @JsonProperty(value = "updated_by")
    private String updatedBy;

}
