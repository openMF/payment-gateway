package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sanyam Goel created on 12/6/18
 */

@RequestMapping(value = "/vendor-data")
@RestController
public class VendorController {

    @Autowired
    VendorService vendorService;

    // get all vendors
    @RequestMapping(method = RequestMethod.GET, value = "/vendors", produces = "application/json")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        if(vendors.isEmpty()){
            return new ResponseEntity<List<Vendor>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Vendor>>(vendors, HttpStatus.OK);

    }

    // get vendor by id
    @RequestMapping(method = RequestMethod.GET, value = "/vendor/{id}", produces = "application/json")
    public ResponseEntity<Vendor> getVendorById(
            @PathVariable("id") long id
    ) {
        Vendor vendor = vendorService.getVendorById(id);
        if (vendor == null) {
            System.out.println("Vendor with id " + id + " not found");
            return new ResponseEntity<Vendor>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    // get All active vendors
    @RequestMapping(method = RequestMethod.GET, value = "/active-vendors", produces = "application/json")
    public ResponseEntity<List<Vendor>> getAllActiveVendors() {
        List<Vendor> vendors = vendorService.getAllActiveVendors();
        if(vendors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }
}
