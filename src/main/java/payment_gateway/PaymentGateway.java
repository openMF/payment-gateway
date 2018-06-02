package payment_gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Rahul Goel created on 2/6/18
 */

@SpringBootApplication
@EnableAspectJAutoProxy
public class PaymentGateway {

    private static Logger logger = LogManager.getLogger(PaymentGateway.class);

    public static void main(String[] args) {
        logger.info("Starting payment-gateway application");
        SpringApplication.run(PaymentGateway.class, args);
        logger.info("Payment-gateway application started");
    }
}
