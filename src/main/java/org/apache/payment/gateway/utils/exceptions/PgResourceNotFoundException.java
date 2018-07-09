package org.apache.payment.gateway.utils.exceptions;

/**
 * @author Rahul Goel created on 3/6/18
 */


public class PgResourceNotFoundException extends PgBaseException {

    public PgResourceNotFoundException() {
    }

    public PgResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PgResourceNotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

    public PgResourceNotFoundException(String message) {
        super(message);
    }

    public PgResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public PgResourceNotFoundException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
