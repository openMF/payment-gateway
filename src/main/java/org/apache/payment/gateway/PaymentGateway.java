package org.apache.payment.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Rahul Goel created on 2/6/18
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableRetry
@EnableAsync
public class PaymentGateway {

    private static Logger logger = LogManager.getLogger(PaymentGateway.class);

    public static void main(String[] args) {
        logger.info("Starting payment-gateway application");
        SpringApplication.run(PaymentGateway.class, args);
        logger.info("Payment-gateway application started");
    }
}
