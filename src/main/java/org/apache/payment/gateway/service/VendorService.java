package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.domains.Vendor;
import org.apache.payment.gateway.dto.VendorDTO;
import org.apache.payment.gateway.repository.VendorRepository;
import org.apache.payment.gateway.utils.Utility;
import org.apache.payment.gateway.utils.exceptions.PgResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sanyam Goel created on 13/6/18
 */

@Service
public class VendorService {

    Utility utility = new Utility();

    @Autowired
    VendorRepository vendorRepository;

    @Loggable
    public List<VendorDTO> getAllVendors() {
        List<Vendor> vendors = vendorRepository.getList(Vendor.class);
        List<VendorDTO> vendorsDTO = utility.convertModelList(vendors, VendorDTO.class);
        if(vendorsDTO == null || vendorsDTO.isEmpty()) {
            throw new PgResourceNotFoundException("Vendor List not found");
        }
        return vendorsDTO;
//        List<Vendor> vendors = vendorRepository.getList(Vendor.class);
//        return utility.convertModelList(vendors, VendorDTO.class);
    }

    @Loggable
    public VendorDTO getVendorById(long id) {
        Vendor vendor = vendorRepository.getById(id, Vendor.class);
        VendorDTO vendorDTO = utility.convertModel(vendor, VendorDTO.class);;
        if(vendorDTO == null){
            throw new PgResourceNotFoundException("Vendor not found for given id " + id);
        }
        return vendorDTO;
    }

    @Loggable
    public List<VendorDTO> getAllActiveVendors() {
        List<Vendor> vendors = vendorRepository.getAllActiveVendors();
        List<VendorDTO> vendorsDTO = utility.convertModelList(vendors, VendorDTO.class);
        if(vendorsDTO == null || vendorsDTO.isEmpty()) {
            throw new PgResourceNotFoundException("Active vendor List not found");
        }
        return vendorsDTO;
//
//        List<Vendor> vendors = vendorRepository.getAllActiveVendors();
//        return utility.convertModelList(vendors, VendorDTO.class);
//        return vendorRepository.getAllActiveVendors();
    }

}
