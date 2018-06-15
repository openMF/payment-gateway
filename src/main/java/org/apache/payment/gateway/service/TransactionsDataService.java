package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.domains.TransactionData;
import org.apache.payment.gateway.repository.TransactionsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@Service
public class TransactionsDataService {

    @Autowired
    TransactionsDataRepository transactionsDataRepository;

    @Loggable
    public List<TransactionData> getAllTransactions() {
        return transactionsDataRepository.getAll();
    }

}
