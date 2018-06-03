package org.apache.payment.gateway.config.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.payment.gateway.utils.exceptions.PgHibernateException;
import org.apache.payment.gateway.utils.exceptions.PgResourceNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * @author Rahul Goel created on 3/6/18
 */

@Repository
public class AbstractBaseRepository {

    private static Logger logger = LogManager.getLogger(AbstractBaseRepository.class);

    private ThreadLocal<Session> currentSession = new ThreadLocal();
    @PersistenceContext
    EntityManager entityManager;

    public AbstractBaseRepository() {
    }

    public Session getCurrentSession() {
        Session session = (Session)this.currentSession.get();
        return null != session ? session : (Session)this.entityManager.unwrap(Session.class);
    }

    public <T> T getById(Serializable id, Class<T> clz) {
        T output = this.getCurrentSession().get(clz, id);
        if (output == null) {
            throw new PgResourceNotFoundException(String.format("resource for %s with id %s does not exists", clz.getName(), id.toString()));
        } else {
            return output;
        }
    }

    public Serializable create(Object entity) {
        try {
            return this.getCurrentSession().save(entity);
        } catch (ConstraintViolationException var3) {
            throw new PgResourceNotFoundException(String.format("unable to create resource. constraint voilation exception for %s.", entity.toString()), var3);
        } catch (HibernateException var4) {
            throw new PgHibernateException(String.format("Hibernate Exception occurred with cause %s", var4.getMessage()), var4);
        }
    }

    public void update(Object entity) {
        try {
            this.getCurrentSession().update(entity);
        } catch (HibernateException var3) {
            throw new PgHibernateException(String.format("Hibernate Exception occurred with cause %s", var3.getMessage()), var3);
        }
    }

}
