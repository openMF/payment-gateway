package org.apache.payment.gateway.repository;

import org.apache.payment.gateway.config.hibernate.AbstractBaseRepository;
import org.apache.payment.gateway.domains.Vendor;
import org.hibernate.SessionFactory;
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

//    @PersistenceContext
//    EntityManager entityManager;
//
//    public List<Vendor> getAll() {
//
//        return entityManager.createQuery("select * from vendor").getResultList();
//    }


    public List<Vendor> getAll() {
        List<Vendor> output = this.getCurrentSession().createCriteria(Vendor.class).list();
        return output;
    }

    public List<Vendor> getAllActiveVendors() {
        List<Vendor> output = (List<Vendor>) this.getCurrentSession().createSQLQuery("Select * from vendor where is_active = 1").list();
        return output;
    }

}
