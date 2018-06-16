package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.config.hibernate.AbstractBaseRepository;
import org.apache.payment.gateway.domains.Vendor;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanyam Goel created on 14/6/18
 */

@Repository
public class VendorRepository extends AbstractBaseRepository {
//     db layer
//    All Database interaction is done via repository


//    public List<Vendor> getAllVendors() {
//        List<Vendor> output = this.getCurrentSession().createCriteria(Vendor.class).list();
//        return output;
//    }



    public List<Vendor> getAllActiveVendors() {
        Criteria criteria = this.getCurrentSession().createCriteria(Vendor.class);
        criteria.add(Restrictions.eq("isActive", true));
        return criteria.list();

    }

}
