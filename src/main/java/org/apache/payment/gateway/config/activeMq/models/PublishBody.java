package org.apache.payment.gateway.config.activeMq.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rahul Goel created on 10/6/18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishBody {
    String className;
    String payload;
}
