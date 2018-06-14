package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.service.TestService;
import org.apache.payment.gateway.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Rahul Goel created on 2/6/18
 */

@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public ResponseEntity<Object> getTestEndpoint(
            @RequestParam(required = false) String testParam
    ){
        return new ResponseEntity<>(testService.testServiceMethod(testParam), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/vendor", produces = "application/json")
    public ResponseEntity<Object> setVendor(
            @RequestParam(required = false) String vendorName
    ){
        return new ResponseEntity<>(testService.setVendor(vendorName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/vendor", produces = "application/json")
    public ResponseEntity<Object> getVendor(
            @RequestParam(required = false) String vendorName
    ){
        return new ResponseEntity<>(testService.getVendor(vendorName), HttpStatus.OK);
    }

}