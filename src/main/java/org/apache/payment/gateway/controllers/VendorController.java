package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.dto.VendorDTO;
import org.apache.payment.gateway.dtos.response.ResponseModel;
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

@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class VendorController extends RestResponseHandler {

    @Autowired
    VendorService vendorService;

    // get all vendors
    @RequestMapping(method = RequestMethod.GET, value = "/vendors", produces = "application/json")
    public ResponseEntity<ResponseModel<List<VendorDTO>>> getAllVendors() {
        List<VendorDTO> vendors = vendorService.getAllVendors();
        return super.responseStandardizer(vendors);
    }

    // get vendor by id
    @RequestMapping(method = RequestMethod.GET, value = "/vendor/{id}", produces = "application/json")
    public ResponseEntity<ResponseModel<VendorDTO>> getVendorById(
            @PathVariable("id") long id
    ) {
        VendorDTO vendor = vendorService.getVendorById(id);
        return super.responseStandardizer(vendor);
    }

    // get all active vendors
    @RequestMapping(method = RequestMethod.GET, value = "/active-vendors", produces = "application/json")
    public ResponseEntity<ResponseModel<List<VendorDTO>>> getAllActiveVendors() {
        List<VendorDTO> vendors = vendorService.getAllActiveVendors();
        return super.responseStandardizer(vendors);
    }
}
