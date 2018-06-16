package org.apache.payment.gateway.enums;

/**
 * @author Rahul Goel created on 16/6/18
 */


public enum PgExceptionType {

    NoHandlerFoundException,
    Exception,
    HttpRequestMethodNotSupportedException,
    MethodArgumentNotValidException,
    MissingServletRequestParameterException,
    ConstraintViolationException,
    HttpMessageNotReadableException,
    HttpMediaTypeNotSupportedException,

    PgActiveMqException,
    PgHibernateException,
    PgResourceNotFoundException;

    private PgExceptionType(){

    }
}
