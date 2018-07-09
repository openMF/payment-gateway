package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.domains.TransactionData;
import org.apache.payment.gateway.dto.TransactionDTO;
import org.apache.payment.gateway.dto.TransactionsResponseDTO;
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
    public List<TransactionDTO> getTransactionByVendorReferenceId(String vendorRefId) {
        List<TransactionData> transactionData = transactionsDataRepository.getTransactionByVendorReferenceId(vendorRefId);
        List<TransactionDTO> transactionDTOS = utility.convertModelList(transactionData, TransactionDTO.class);
        return transactionDTOS;
    }

    @Loggable
    public TransactionsResponseDTO getTransactions(long nextTransactionId, int size, boolean isTotalCountRequired, List<Long> vendorIdList, String clientPhoneNumber, String clientAccoutNumber) {
        List<TransactionData> transactionData = transactionsDataRepository.getTransactionsFromDb(nextTransactionId, size, vendorIdList, clientPhoneNumber, clientAccoutNumber);
        List<TransactionDTO> transactionDTOS = utility.convertModelList(transactionData, TransactionDTO.class);

        if(transactionDTOS == null || transactionDTOS.isEmpty()) {
            throw new PgResourceNotFoundException("Transaction List not found");
        }

        Integer totalCount = null;
        if(isTotalCountRequired){
            totalCount = transactionsDataRepository.getTotalCount();
        }

        long nextComputedTransactionId = -1;
        int transactionListSize = transactionDTOS.size();
        nextComputedTransactionId =  transactionListSize < size ? -1 : transactionDTOS.get(transactionListSize - 1).getTransactionId();
        return new TransactionsResponseDTO(transactionDTOS, nextComputedTransactionId, totalCount);
    }

    @Loggable
    public TransactionDTO getTransactionByTransactionId(long transactionId) {
        TransactionData transactionData = transactionsDataRepository.getById(transactionId, TransactionData.class);
        TransactionDTO transactionDTO = utility.convertModel(transactionData, TransactionDTO.class);

        if(transactionDTO == null){
            throw new PgResourceNotFoundException("Transaction not found for given id " + transactionId);
        }

        return transactionDTO;
    }
}
