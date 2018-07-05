package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.config.hibernate.AbstractBaseRepository;
import org.apache.payment.gateway.domains.TransactionData;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sanyam Goel created on 15/6/18
 */

@Repository
public class TransactionsDataRepository extends AbstractBaseRepository {

//     public List<TransactionData> getAllTransactions() {
////        Criteria criteria = this.getCurrentSession().createCriteria(TransactionData.class);
//        return this.getCurrentSession().createCriteria(TransactionData.class).list();
////        List<TransactionData> output = (List<TransactionData>) this.getCurrentSession().createSQLQuery("Select * from transaction_data").list();
////        List<TransactionData> output = this.getCurrentSession().createCriteria(TransactionData.class).list();
////        return output;
//    }


    // Get Transaction by Vendor Reference ID
    public TransactionData getTransactionByVendorReferenceId(String vendorRefId) {
//        TransactionData output = this.getCurrentSession().createCriteria(TransactionData.class);
        TransactionData output = (TransactionData) this.getCurrentSession().createSQLQuery("Select * from transaction_data where vendor_reference_id = " + vendorRefId);
        return output;
    }

//    public List<TransactionData> getAllTransaction(){
//        Criteria criteria = this.getCurrentSession().createCriteria(TransactionData.class);
//
//    }

}
