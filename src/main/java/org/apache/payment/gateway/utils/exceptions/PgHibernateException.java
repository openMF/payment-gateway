package org.apache.payment.gateway.utils.exceptions;

/**
 * @author Rahul Goel created on 3/6/18
 */


public class PgHibernateException extends PgBaseException {
    public PgHibernateException() {
    }

    public PgHibernateException(String message, Throwable cause) {
        super(message, cause);
    }

    public PgHibernateException(String message, String errorCode) {
        super(message, errorCode);
    }

    public PgHibernateException(String message) {
        super(message);
    }

    public PgHibernateException(Throwable cause) {
        super(cause);
    }

    public PgHibernateException(String message, String errorCode, Throwable cause) {
        super(message, errorCode, cause);
    }
}
