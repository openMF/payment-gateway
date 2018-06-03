package org.apache.payment.gateway.utils.exceptions;

/**
 * @author Rahul Goel created on 3/6/18
 */


public class PgBaseException extends RuntimeException{

    private String errorCode;

    public PgBaseException(){
    }

    public PgBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public PgBaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public PgBaseException(String message) {
        super(message);
    }

    public PgBaseException(Throwable cause) {
        super(cause);
    }

    public PgBaseException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getDeveloperCode() {
        return this.errorCode;
    }

    public void setDeveloperCode(String errorCode) {
        this.errorCode = errorCode;
    }




}
