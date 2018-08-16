package org.apache.payment.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author Sanyam Goel created on 7/7/18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionsResponseDTO {

    private List<TransactionDTO> transactionList;

    private long nextTransactionId;

    private Integer totalCount;
}
