package org.apache.payment.gateway.config.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.payment.gateway.enums.ErrorCodes;
import org.apache.payment.gateway.utils.exceptions.PgActiveMqException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import javax.jms.Connection;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author Rahul Goel created on 10/6/18
 */


public abstract class AbstractActiveMqBase implements ExceptionListener {

    protected ActiveMQConnectionFactory connectionFactory;
    protected Connection connection;
    protected Session session;
    private final Logger logger = LoggerFactory.getLogger(AbstractActiveMqBase.class);

    protected String ACTIVEMQ_HOST;

    @Retryable(
            value = { PgActiveMqException.class },
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000)
    )
    public void init() {
        try {
            logger.info("ActiveMq initiating consumer");
            connectionFactory = ActiveMqConfig.getConnectionFactory();
            logger.info("initiating connection to " + ActiveMqConfig.ACTIVEMQ_HOST + ":" + ActiveMqConfig.ACTIVEMQ_PORT);
            connection = connectionFactory.createConnection();
            connection.start();
            logger.info("connected to " + ActiveMqConfig.ACTIVEMQ_HOST + ":" + ActiveMqConfig.ACTIVEMQ_PORT);
            connection.setExceptionListener(this);
            createSession(connection);
        } catch (Exception e) {
            logger.error("failed to connected to " + ActiveMqConfig.ACTIVEMQ_HOST + ":" + ActiveMqConfig.ACTIVEMQ_PORT, e);
            throw new PgActiveMqException(ErrorCodes.ACTIVEMQ_001.getDescription(), ErrorCodes.ACTIVEMQ_001.getDescription());
        }
    }

    @Override
    public void onException(JMSException exception) {
        logger.error(exception.toString());
    }

    protected abstract void createSession(Connection connection) throws Exception;
}
