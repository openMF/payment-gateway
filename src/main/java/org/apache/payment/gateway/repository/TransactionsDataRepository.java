package org.apache.payment.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.apache.payment.gateway.domains.TransactionData;
import org.apache.payment.gateway.dto.request.TransactionRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

public interface TransactionsDataRepository extends JpaRepository<TransactionData, Long> {


    @Query("select td from TransactionData td where td.vendorReferenceId=:vendorRefId")
    List<TransactionData> getTransactionByVendorReferenceId(@Param("vendorRefId") Long vendorRefId);


}
