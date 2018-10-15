package org.apache.payment.gateway.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.dto.response.ErrorResponseModel;
import org.apache.payment.gateway.dto.response.ResponseModel;
import org.apache.payment.gateway.enums.PgExceptionType;
import org.apache.payment.gateway.utils.exceptions.PgResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * @author Rahul Goel created on 16/6/18
 */

@ControllerAdvice
public class RestResponseHandler {

    private static Logger logger = LogManager.getLogger(RestResponseHandler.class);

    RestResponseHandler() {

    }

    /*
     * functions which wrap response object into payment-gateway standard response.
     *
     * */
    public <T> ResponseEntity<ResponseModel<T>> responseStandardizer(T object, HttpStatus httpStatus) {
        ResponseModel<T> responseModel = new ResponseModel(object, httpStatus.value(), null);
        return new ResponseEntity(responseModel, httpStatus);
    }

    public <T> ResponseEntity<ResponseModel<T>> responseStandardizer(T object) {
        return this.responseStandardizer(object, HttpStatus.OK);
    }

    /*
     * functions for handling spring exceptions
     *
     * */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseBody
    public ResponseModel<Object> NotFoundHandler(NoHandlerFoundException ex) {
        logger.error("Exception : ", ex);
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.NoHandlerFoundException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL()));
        return new ResponseModel((Object) null, HttpStatus.NOT_FOUND.value(), error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseModel<Object> ExceptionHandler(Exception ex) {
        logger.error("Exception : ", ex);
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.Exception, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors(ex.getMessage()));
        return new ResponseModel((Object) null, HttpStatus.INTERNAL_SERVER_ERROR.value(), error);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseModel<Object> HttpRequestMethodNotSupportedHandler(HttpRequestMethodNotSupportedException ex) {
        logger.error("Exception : ", ex);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request endpoint");
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.HttpRequestMethodNotSupportedException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors(builder.toString()));
        return new ResponseModel((Object) null, HttpStatus.METHOD_NOT_ALLOWED.value(), error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResponseModel<Object> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        logger.error("Exception : ", ex);
        ArrayList<String> errors = new ArrayList();
        Iterator var5 = ex.getBindingResult().getAllErrors().iterator();
        while (var5.hasNext()) {
            ObjectError error = (ObjectError) var5.next();
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.MethodArgumentNotValidException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), errors);
        return new ResponseModel((Object) null, HttpStatus.BAD_REQUEST.value(), error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseModel<Object> MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.error("Exception : ", ex);
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.MissingServletRequestParameterException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors(ex.getParameterName() + " parameter is missing"));
        return new ResponseModel((Object) null, HttpStatus.BAD_REQUEST.value(), error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseModel<Object> ConstraintViolationExceptionCustomHandler(ConstraintViolationException ex) {
        logger.error("Exception : ", ex);
        ArrayList<String> errors = new ArrayList();
        Iterator var5 = ex.getConstraintViolations().iterator();

        while (var5.hasNext()) {
            ConstraintViolation<?> violation = (ConstraintViolation) var5.next();
            errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.ConstraintViolationException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), errors);
        return new ResponseModel((Object) null, HttpStatus.BAD_REQUEST.value(), error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseModel<Object> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        logger.error("Exception : ", ex);
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.HttpMessageNotReadableException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors(ex.getMessage()));
        return new ResponseModel((Object) null, HttpStatus.BAD_REQUEST.value(), error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    public ResponseModel<Object> HttpMediaTypeNotSupportedHandler(HttpMediaTypeNotSupportedException ex) {
        logger.error("Exception : ", ex);
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported");
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.HttpMediaTypeNotSupportedException, (String) null, ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors(builder.toString()));
        return new ResponseModel((Object) null, HttpStatus.BAD_REQUEST.value(), error);
    }

    /*
     * functions for handling payment-gateway custom exceptions.
     *
     * */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({PgResourceNotFoundException.class})
    @ResponseBody
    public ResponseModel<Object> resourceNotFoundExceptionHandler(PgResourceNotFoundException ex) {
        logger.error("Exception : ", ex);
        ErrorResponseModel error = new ErrorResponseModel(PgExceptionType.PgResourceNotFoundException, (String) ex.getErrorCode(), ex.getMessage(), Instant.now().toEpochMilli(), this.getErrors(ex.getMessage()));
        return new ResponseModel((Object) null, HttpStatus.NOT_FOUND.value(), error);
    }



    /*
     * Utility function specific to this class
     *
     * */
    private ArrayList<String> getErrors(String... errors) {
        ArrayList<String> errorMessages = new ArrayList();
        Collections.addAll(errorMessages, errors);
        return errorMessages;
    }

}

