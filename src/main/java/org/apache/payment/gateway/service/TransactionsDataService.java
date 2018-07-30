package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.constants.TransactionStatus;
import org.apache.payment.gateway.domains.TransactionData;
import org.apache.payment.gateway.dto.TransactionDTO;
import org.apache.payment.gateway.dto.TransactionsResponseDTO;
import org.apache.payment.gateway.dto.beyonic.response.CollectionNotifyResponse;
import org.apache.payment.gateway.dto.request.TransactionRequest;
import org.apache.payment.gateway.dto.response.TransactionResponse;
import org.apache.payment.gateway.repository.TransactionsDataRepository;
import org.apache.payment.gateway.service.beyonic.BeyonicService;
import org.apache.payment.gateway.utils.Utility;
import org.apache.payment.gateway.utils.exceptions.PgResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@Service
public class TransactionsDataService {

    @Autowired
    TransactionsDataRepository transactionsDataRepository;

    @Autowired
    BeyonicService beyonicService;

    /**
     * Get transaction by a vendor referenceID
     *
     * @param vendorRefId
     * @return
     */
    @Loggable
    public List<TransactionDTO> getTransactionByVendorReferenceId(String vendorRefId) {
        List<TransactionData> transactionData = transactionsDataRepository.getTransactionByVendorReferenceId(vendorRefId);
        List<TransactionDTO> transactionDTOS = Utility.convertModelList(transactionData, TransactionDTO.class);
        return transactionDTOS;
    }

    @Loggable
    public TransactionsResponseDTO getTransactions(long nextTransactionId, int size, boolean isTotalCountRequired, List<Long> vendorIdList, String clientPhoneNumber, String clientAccoutNumber) {
        List<TransactionData> transactionData = transactionsDataRepository.getTransactionsFromDb(nextTransactionId, size, vendorIdList, clientPhoneNumber, clientAccoutNumber);
        List<TransactionDTO> transactionDTOS = Utility.convertModelList(transactionData, TransactionDTO.class);

        if (transactionDTOS == null || transactionDTOS.isEmpty()) {
            throw new PgResourceNotFoundException("Transaction List not found");
        }

        Integer totalCount = null;
        if (isTotalCountRequired) {
            totalCount = transactionsDataRepository.getTotalCount();
        }

        long nextComputedTransactionId = -1;
        int transactionListSize = transactionDTOS.size();
        nextComputedTransactionId = transactionListSize < size ? -1 : transactionDTOS.get(transactionListSize - 1).getTransactionId();
        return new TransactionsResponseDTO(transactionDTOS, nextComputedTransactionId, totalCount);
    }

    @Loggable
    public TransactionDTO getTransactionByTransactionId(long transactionId) {
        TransactionData transactionData = transactionsDataRepository.getById(transactionId, TransactionData.class);
        TransactionDTO transactionDTO = Utility.convertModel(transactionData, TransactionDTO.class);

        if (transactionDTO == null) {
            throw new PgResourceNotFoundException("Transaction not found for given id " + transactionId);
        }

        return transactionDTO;
    }

    @Transactional
    @Loggable
    public TransactionResponse postTransactionDetails(TransactionRequest transactionRequest) {
        /*
         * TODO to create clientID in transaction Domain,
         * */

        CollectionNotifyResponse collectionNotifyResponse = beyonicService.createCollectionRequest(transactionRequest);

//        db call
        TransactionData transactionData = new TransactionData();

        transactionData.setTransactionAmount(transactionRequest.getAmount());
        transactionData.setClientFirstName(transactionRequest.getFirstName());
        transactionData.setClientLastName(transactionRequest.getLastName());
        transactionData.setClientPhoneNumber(transactionRequest.getPhoneNumber());
        transactionData.setCurrencyType(transactionRequest.getCurrency());
        transactionData.setFineractClientId(transactionRequest.getClientId());
        transactionData.setVendorId(transactionRequest.getVendorId());
//        transactionData.setVendorMetaData(transactionRequest.getMetaData());
        transactionData.setVendorReferenceId(String.valueOf(collectionNotifyResponse.getId()));
        transactionData.setAccountType("SAVINGS");

        transactionsDataRepository.create(transactionData);

        TransactionResponse transactionResponse = Utility.convertModel(transactionRequest, TransactionResponse.class);
        transactionResponse.setPaymentStatus(TransactionStatus.PAYMENT_INITIATED);
        transactionResponse.setReferenceId(String.valueOf(collectionNotifyResponse.getId()));
        return transactionResponse;
    }
}
