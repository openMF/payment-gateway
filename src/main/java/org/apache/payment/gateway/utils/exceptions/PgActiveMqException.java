package org.apache.payment.gateway.utils.exceptions;

/**
 * @author Rahul Goel created on 10/6/18
 */


public class PgActiveMqException extends PgBaseException {
    public PgActiveMqException() {
    }

    public PgActiveMqException(String message, Throwable cause) {
        super(message, cause);
    }

    public PgActiveMqException(String message, String errorCode) {
        super(message, errorCode);
    }

    public PgActiveMqException(String message) {
        super(message);
    }

    public PgActiveMqException(Throwable cause) {
        super(cause);
    }

    public PgActiveMqException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
