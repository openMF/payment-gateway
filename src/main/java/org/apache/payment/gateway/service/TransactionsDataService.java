package org.apache.payment.gateway.service;

import com.google.gson.Gson;
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

import org.apache.payment.gateway.activeMQ.config.OutboundChannelHelper;
import org.apache.payment.gateway.activeMQ.util.PaymentGatewayConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@Service
public class TransactionsDataService {

    @Autowired
    TransactionsDataRepository transactionsDataRepository;

    @Autowired
    OutboundChannelHelper outboundChannelHelper;

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
        List<TransactionData> transactionData = transactionsDataRepository.getTransactionByVendorReferenceId((long) Integer.parseInt(vendorRefId));
        List<TransactionDTO> transactionDTOS = Utility.convertModelList(transactionData, TransactionDTO.class);
        return transactionDTOS;
    }

    /**
     * Get Transactions
     *
     * @param nextTransactionId
     * @param size
     * @param isTotalCountRequired
     * @param vendorIdList
     * @param clientPhoneNumber
     * @param clientAccoutNumber
     * @return
     */
    @Loggable
    public TransactionsResponseDTO getTransactions(long nextTransactionId, int size, boolean isTotalCountRequired, List<Long> vendorIdList, String clientPhoneNumber, String clientAccoutNumber) {
        List<TransactionData> transactionData = transactionsDataRepository.findAll();  //getTransactionsFromDb(nextTransactionId, size, vendorIdList, clientPhoneNumber, clientAccoutNumber);
        List<TransactionDTO> transactionDTOS = Utility.convertModelList(transactionData, TransactionDTO.class);

        if (transactionDTOS == null || transactionDTOS.isEmpty()) {
            throw new PgResourceNotFoundException("Transaction List not found");
        }

        long totalCount = 0;
        if (isTotalCountRequired) {
            totalCount = transactionsDataRepository.count();
        }

        long nextComputedTransactionId = -1;
        int transactionListSize = transactionDTOS.size();
        nextComputedTransactionId = transactionListSize < size ? -1 : transactionDTOS.get(transactionListSize - 1).getTransactionId();
        return new TransactionsResponseDTO(transactionDTOS, nextComputedTransactionId, (int) totalCount);
    }

    @Loggable
    public TransactionDTO getTransactionByTransactionId(long transactionId) {
        TransactionData transactionData = transactionsDataRepository.findOne(transactionId);
        TransactionDTO transactionDTO = Utility.convertModel(transactionData, TransactionDTO.class);

        if (transactionDTO == null) {
            throw new PgResourceNotFoundException("Transaction not found for given id " + transactionId);
        }

        return transactionDTO;
    }

    /**
     * Create transaction from user
     * @param transactionRequest
     * @return
     */
    @Transactional
    @Loggable
    public TransactionResponse postTransactionDetails(TransactionRequest transactionRequest) {
        /*
         * TODO to create clientID in transaction Domain,
         * */

        CollectionNotifyResponse collectionNotifyResponse = beyonicService.createCollectionRequest(transactionRequest);

        // Send ActiveMQ message to Fineract
        Map<String, Object> paymentMap = new HashMap<>();
        paymentMap.put("transactionType", PaymentGatewayConstants.TRANSACTION_TYPE_LOAN_REPAYMENT);
        paymentMap.put("channelId", transactionRequest.getVendorId());
        paymentMap.put("transactionReference", String.valueOf(collectionNotifyResponse.getId()));
        paymentMap.put("mobileNo", transactionRequest.getPhoneNumber());
        paymentMap.put("destAccount", transactionRequest.getClientId());
        paymentMap.put("amount", transactionRequest.getAmount());
        // TODO: set locale
        paymentMap.put("locale", "en");

        final String jsonPayment = new Gson().toJson(paymentMap);
        // send payment to queue
        outboundChannelHelper.sendMessage(PaymentGatewayConstants.CHANNEL_INBOUND_USAGE, jsonPayment);

        //  db call
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

        transactionsDataRepository.save(transactionData);

        TransactionResponse transactionResponse = Utility.convertModel(transactionRequest, TransactionResponse.class);
        transactionResponse.setPaymentStatus(TransactionStatus.PAYMENT_INITIATED);
        transactionResponse.setReferenceId(String.valueOf(collectionNotifyResponse.getId()));
        return transactionResponse;
    }
}
