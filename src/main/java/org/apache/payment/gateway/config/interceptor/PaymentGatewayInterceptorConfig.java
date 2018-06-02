package org.apache.payment.gateway.config.interceptor;

import org.apache.payment.gateway.constants.InterceptorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Rahul Goel created on 2/6/18
 */

@Configuration
public class PaymentGatewayInterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    PaymentGatewayInterceptor paymentGatewayInterceptor;

    public PaymentGatewayInterceptorConfig(){ }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(paymentGatewayInterceptor).addPathPatterns(InterceptorConstants.INTERCEPTOR_PATTERNS.split(","));
    }

}
