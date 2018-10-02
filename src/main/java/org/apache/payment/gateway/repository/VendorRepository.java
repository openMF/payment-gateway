package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.domains.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Sanyam Goel created on 14/6/18
 */

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Query("select vendor from Vendor vendor where vendor.isActive=1")
    List<Vendor> getAllActiveVendors();

    @Query("select vendor from Vendor vendor where vendor.vendorId=:vendorId")
    Vendor getVendorById(@Param("vendorId") Long vendorId);

}
