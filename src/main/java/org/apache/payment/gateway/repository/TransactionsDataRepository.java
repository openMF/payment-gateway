package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.config.hibernate.AbstractBaseRepository;
import org.apache.payment.gateway.domains.TransactionData;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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

    public List<TransactionData> getTransactionsFromDb(long nextTransactionId, int limit, List<Long> vendorIdList) {
        /*
          select COUNT(*) From Transaction_data
          Integer count = ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
          SELECT * FROM `transaction_data` LIMIT 5 OFFSET 0; starting from 0 to 5
          .setFirstResult(firstResult||offset).setMaxResults(limit);
        */
        Criteria criteria = this.getCurrentSession().createCriteria(TransactionData.class);
        criteria.add(Restrictions.gt("transactionId", nextTransactionId));

        if(vendorIdList != null && !vendorIdList.isEmpty()){
            criteria.add(Restrictions.in("vendorId", vendorIdList));
        }

        criteria.setMaxResults(limit);
        return criteria.list();
    }

    public Integer getTotalCount() {
        Criteria criteria = this.getCurrentSession().createCriteria(TransactionData.class);
        return ((Number)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }


    // Get Transaction by Vendor Reference ID
    public List<TransactionData> getTransactionByVendorReferenceId(String vendorRefId) {
//        TransactionData output = this.getCurrentSession().createCriteria(TransactionData.class);
//        TransactionData output = (TransactionData) this.getCurrentSession().createSQLQuery("Select * from transaction_data where vendor_reference_id = " + vendorRefId);
//        return output;
//        select * from transaction_data where vendor_reference_id = "dontknow1";
        Criteria criteria = this.getCurrentSession().createCriteria(TransactionData.class);
        criteria.add(Restrictions.eq("vendorReferenceId", vendorRefId));
        return criteria.list();
    }

}
