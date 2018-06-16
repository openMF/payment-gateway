package org.apache.payment.gateway.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Rahul Goel created on 16/6/18
 */

@Data
@AllArgsConstructor
public class ErrorResponseModel {
    private String errorCode;
    private String errorMessage;
    private String errorCause;
}
