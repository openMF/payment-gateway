package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.domains.TransactionData;
import org.apache.payment.gateway.dto.TransactionDTO;
import org.apache.payment.gateway.repository.TransactionsDataRepository;
import org.apache.payment.gateway.utils.Utility;
import org.apache.payment.gateway.utils.exceptions.PgResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@Service
public class TransactionsDataService {

    Utility utility = new Utility();

    @Autowired
    TransactionsDataRepository transactionsDataRepository;

    @Loggable
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionData> transactionData = transactionsDataRepository.getList(TransactionData.class);
        List<TransactionDTO> transactionDTO = utility.convertModelList(transactionData, TransactionDTO.class);
        if(transactionDTO == null || transactionDTO.isEmpty()) {
            throw new PgResourceNotFoundException("Transaction List not found");
        }
        return transactionDTO;
//        return transactionsDataRepository.getList(TransactionData.class);
    }

    @Loggable
    public TransactionData getTransactionByVendorReferenceId(String vendorRefId) {
        return transactionsDataRepository.getTransactionByVendorReferenceId(vendorRefId);
    }
}
