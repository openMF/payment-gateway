package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.dtos.response.ResponseModel;
import org.apache.payment.gateway.enums.ErrorCodes;
import org.apache.payment.gateway.service.TestService;
import org.apache.payment.gateway.utils.exceptions.PgResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Rahul Goel created on 2/6/18
 */

@ApiIgnore
@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class TestController extends RestResponseHandler {

    @Autowired
    TestService testService;

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public ResponseEntity<Object> getTestEndpoint(
            @RequestParam(required = false) String testParam
    ){
        throw new PgResourceNotFoundException(ErrorCodes.ACTIVEMQ_001.getDescription(), ErrorCodes.ACTIVEMQ_001.getCode());
//        return new ResponseEntity<>(testService.testServiceMethod(testParam), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vendor", produces = "application/json")
    public ResponseEntity<ResponseModel<Vendor>> setVendor(
            @RequestParam(required = false) String vendorName
    ){
        return super.responseStandardizer(testService.setVendor(vendorName));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/vendor", produces = "application/json")
    public ResponseEntity<Object> getVendor(
            @RequestParam(required = false) String vendorName
    ){
        return new ResponseEntity<>(testService.getVendor(vendorName), HttpStatus.OK);
    }

}