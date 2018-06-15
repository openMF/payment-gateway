package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.config.hibernate.AbstractBaseRepository;
import org.apache.payment.gateway.domains.TransactionData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@Repository
public class TransactionsDataRepository extends AbstractBaseRepository {

    public List<TransactionData> getAll() {
        List<TransactionData> output = (List<TransactionData>) this.getCurrentSession().createSQLQuery("Select * from transaction_data").list();
//        List<TransactionData> output = this.getCurrentSession().createCriteria(TransactionData.class).list();
        return output;
    }

}
