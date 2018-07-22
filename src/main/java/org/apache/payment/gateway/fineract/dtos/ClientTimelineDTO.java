package org.apache.payment.gateway.fineract.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientTimelineDTO {

    String submittedOnDate; // Todo -> check with API-DOCS
    String submittedByUsername;
    String submittedByFirstname;
    String submittedByLastname;
    String activatedOnDate; // Todo
    String activatedByUsername;
    String activatedByFirstname;
    String activatedByLastname;

}
