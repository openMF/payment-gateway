package org.apache.payment.gateway.dto.beyonic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sanyam Goel created on 28/7/18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hook {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "user")
    private long userId;

    @JsonProperty(value = "event")
    private String eventType;

    @JsonProperty(value = "created")
    private String createdAt;

    @JsonProperty(value = "updated")
    private String updatedAt;

    @JsonProperty(value = "target")
    private String targetUrl;

}
