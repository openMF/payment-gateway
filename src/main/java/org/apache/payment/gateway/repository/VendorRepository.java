package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.config.hibernate.AbstractBaseRepository;
import org.apache.payment.gateway.domains.Vendor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sanyam Goel created on 14/6/18
 */

@Repository
public class VendorRepository extends AbstractBaseRepository {
//     db layer
//    All Database interaction is done via repository

    public List<Vendor> getAllActiveVendors() {
        Criteria criteria = this.getCurrentSession().createCriteria(Vendor.class);
        criteria.add(Restrictions.eq("isActive", true));
        return criteria.list();

    }

}
