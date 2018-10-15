package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.dto.TransactionDTO;
import org.apache.payment.gateway.dto.request.TransactionRequest;
import org.apache.payment.gateway.dto.response.ResponseModel;
import org.apache.payment.gateway.dto.response.TransactionResponse;
import org.apache.payment.gateway.service.TransactionsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@CrossOrigin(origins = "http://localhost:3100")
@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class TransactionsController extends RestResponseHandler {

    @Autowired
    TransactionsDataService transactionsDataService;

    // search for paginated transactions
    @RequestMapping(method = RequestMethod.GET, value = "/transactions/search", produces = "application/json")
    public ResponseEntity<ResponseModel<Object>> getAllTransactions(
            @RequestParam(required = false, name = "nextTransactionId", defaultValue = "0") long nextTransactionId,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size,
            @RequestParam(required = false, name = "isTotalCountRequired", defaultValue = "false") boolean isTotalCountRequired,
            @RequestParam(required = false, name = "vendorIdList") List<Long> vendorIdList,
            @RequestParam(required = false, name = "clientPhoneNumber") String phoneNumber,
            @RequestParam(required = false, name = "clientAccountNumber") String AccountNumber
    ) {
        return super.responseStandardizer(transactionsDataService.getTransactions(nextTransactionId, size, isTotalCountRequired, vendorIdList, phoneNumber, AccountNumber));
    }

    /*
    @RequestMapping(method = RequestMethod.GET, value = "/transaction-vendor-id/{vendorRefId}", produces = "application/json")
    public ResponseEntity<ResponseModel<List<TransactionDTO>>> getTransactionByVendorReferenceId(
            @PathVariable("vendorId") String vendorRefId
    ) {
        List<TransactionDTO> transactionDTOS = transactionsDataService.getTransactionByVendorReferenceId(vendorRefId);
        return super.responseStandardizer(transactionDTOS);
    }*/

    // get transaction by transaction id
    @RequestMapping(method = RequestMethod.GET, value = "/transaction/{transactionId}", produces = "application/json")
    public ResponseEntity<ResponseModel<TransactionDTO>> getTransactionById(
            @PathVariable("transactionId") long transactionId
    ) {
        TransactionDTO transaction = transactionsDataService.getTransactionByTransactionId(transactionId);
        return super.responseStandardizer(transaction);
    }

    /**
     * to initiate payment from mobile user
     */
    @RequestMapping(method = RequestMethod.POST, value = "/create-transaction")
    public ResponseEntity<ResponseModel<TransactionResponse>> transactionByUser(
            @RequestBody TransactionRequest transactionRequest
    ) {
        TransactionResponse transactionResponse = transactionsDataService.postTransactionDetails(transactionRequest);
        return super.responseStandardizer(transactionResponse);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/loan-disbursal", produces = "application/json")
    public ResponseEntity<ResponseModel<TransactionResponse>> transactionByBank(
            @RequestBody TransactionRequest transactionRequest
    ) {

        return null;
    }
}
