package org.apache.payment.gateway.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.payment.gateway.enums.PgExceptionType;

import java.util.List;

/**
 * @author Rahul Goel created on 16/6/18
 */

@Data
@AllArgsConstructor
public class ErrorResponseModel {
    private PgExceptionType exceptionType;
    private String errorCode;
    private String errorMessage;
    private long time;
    private List<String> errorCause;
}
