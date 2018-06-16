package org.apache.payment.gateway.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author Rahul Goel created on 16/6/18
 */

@Data
@AllArgsConstructor
@ToString
public class ResponseModel<T> {
    private T data;
    private int statusCode;
    private ErrorResponseModel error;
}
