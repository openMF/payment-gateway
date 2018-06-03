package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.repository.PaymentGatewayRepository;
import org.apache.payment.gateway.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rahul Goel created on 2/6/18
 */

@Service
public class TestService {

    @Autowired
    PaymentGatewayRepository paymentGatewayRepository;

    @Loggable
    @Transactional
    public String testServiceMethod(String testParam){

        if(Utility.isNullOrBlank(testParam)){
            return "Everything looks cool!!";
        }else {
            return testParam;
        }
    }

    @Loggable
    @Transactional
    public Vendor setVendor(String vendorName){
        Vendor vendor = new Vendor(vendorName);
        vendor.setId(2);
        paymentGatewayRepository.update(vendor);
        return vendor;
    }

    @Loggable
    public Vendor getVendor(String vendorName) {
        return paymentGatewayRepository.getById(1, Vendor.class);
    }
}
