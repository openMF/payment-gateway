package org.apache.payment.gateway.controllers;

import org.apache.payment.gateway.domains.TransactionData;
import org.apache.payment.gateway.dto.TransactionDTO;
import org.apache.payment.gateway.service.TransactionsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class TransactionsController {

    @Autowired
    TransactionsDataService transactionsDataService;

    // get all transactions
    @RequestMapping(method = RequestMethod.GET, value = "/transactions", produces = "application/json")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
//        List<TransactionData> transactions = transactionsDataService.getAllTransactions();
////        if(transactions.isEmpty()){
////            return new ResponseEntity<List<TransactionData>>(HttpStatus.NO_CONTENT);
////        }
//        return new ResponseEntity<List<TransactionData>>(transactions, HttpStatus.OK);

        List<TransactionDTO> vendors = transactionsDataService.getAllTransactions();
        return new ResponseEntity<List<TransactionDTO>>(vendors, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction-vendor-id/{vendorRefId}", produces = "application/json")
    public ResponseEntity<TransactionData> getTransactionByVendorReferenceId(
            @PathVariable String vendorRefId
    ) {
        TransactionData transactionData = transactionsDataService.getTransactionByVendorReferenceId(vendorRefId);
        if (transactionData == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionData, HttpStatus.OK);
    }
}
