package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.dto.TransactionDTO;
import org.apache.payment.gateway.dtos.response.ResponseModel;
import org.apache.payment.gateway.service.TransactionsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class TransactionsController extends RestResponseHandler {

    @Autowired
    TransactionsDataService transactionsDataService;

    @RequestMapping(method = RequestMethod.GET, value = "/transactions", produces = "application/json")
    public ResponseEntity<ResponseModel<Object>> getAllTransactions(
            @RequestParam(required = false, name = "nextTransactionId", defaultValue = "0") long nextTransactionId,
            @RequestParam(required = false, name = "size", defaultValue = "10") int size,
            @RequestParam(required = false, name = "isTotalCountRequired", defaultValue = "false") boolean isTotalCountRequired
    ) {
        return super.responseStandardizer(transactionsDataService.getTransactions(nextTransactionId, size, isTotalCountRequired));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction-vendor-id/{vendorRefId}", produces = "application/json")
    public ResponseEntity<ResponseModel<List<TransactionDTO>>> getTransactionByVendorReferenceId(
            @PathVariable String vendorRefId
    ) {
        List<TransactionDTO> transactionDTOS = transactionsDataService.getTransactionByVendorReferenceId(vendorRefId);
//        TransactionData transactionData = transactionsDataService.getTransactionByVendorReferenceId(vendorRefId);
//        if (transactionData == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(transactionData, HttpStatus.OK);
        return super.responseStandardizer(transactionDTOS);
    }
}
