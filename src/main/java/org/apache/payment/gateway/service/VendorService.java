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

    @Autowired
    VendorRepository vendorRepository;

    /**
     * to retrieve all vendors
     * @return
     */
    @Loggable
    public List<VendorDTO> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        List<VendorDTO> vendorsDTO = Utility.convertModelList(vendors, VendorDTO.class);

        // It is a valid case that there will be no vendors configured - return an empty list
        //if(vendorsDTO == null || vendorsDTO.isEmpty()) {
        //    throw new PgResourceNotFoundException("Vendor List not found");
        //}

        return vendorsDTO;
    }

    /**
     * get vendor details by vendorID
     * @param id
     * @return
     */
    @Loggable
    public VendorDTO getVendorById(long id) {
        Vendor vendor = vendorRepository.findOne(id);
        VendorDTO vendorDTO = Utility.convertModel(vendor, VendorDTO.class);;

        if(vendorDTO == null){
            throw new PgResourceNotFoundException("Vendor not found for given id " + id);
        }

        return vendorDTO;
    }

    /**
     * Get all active vendors
     * @return
     */
    @Loggable
    public List<VendorDTO> getAllActiveVendors() {
        List<Vendor> vendors = vendorRepository.getAllActiveVendors();
        List<VendorDTO> vendorsDTO = Utility.convertModelList(vendors, VendorDTO.class);

        if(vendorsDTO == null || vendorsDTO.isEmpty()) {
            throw new PgResourceNotFoundException("Active vendor List not found");
        }

        return vendorsDTO;
    }

    /**
     * Create a new vendor
     *
     * @return
     */
    @Loggable
    public VendorDTO createVendor(VendorDTO newVendorDTO) {
        Vendor newVendor = Utility.convertModel(newVendorDTO, Vendor.class);
        Vendor vendor = (Vendor) vendorRepository.save(newVendor);

        VendorDTO vendorDTO = Utility.convertModel(vendor, VendorDTO.class);
        return vendorDTO;
    }
}
