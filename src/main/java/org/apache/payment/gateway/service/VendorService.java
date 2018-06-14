package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.repository.PaymentGatewayRepository;
import org.apache.payment.gateway.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sanyam Goel created on 13/6/18
 */

@Service
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    @Loggable
    public List<Vendor> getAllVendors() {
        return null;
    }

    @Loggable
    public Vendor getVendorById(long id) {
        return vendorRepository.getById(id, Vendor.class);
    }

}
