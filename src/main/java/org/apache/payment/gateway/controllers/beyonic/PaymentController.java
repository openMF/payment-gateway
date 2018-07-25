package org.apache.payment.gateway.controllers.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.response.PaymentListResponse;
import org.apache.payment.gateway.dto.beyonic.response.PaymentResponse;
import org.apache.payment.gateway.service.beyonic.PaymentService;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanyam Goel created on 24/7/18
 */
@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    HttpHeaders headers = new HttpHeaders();

    @RequestMapping(method = RequestMethod.GET, value = "/payments/{id}", produces = "application/json")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable("id") long id
    ) {
        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<PaymentResponse> paymentResponseResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT + "/" + id, HttpMethod.GET, request, PaymentResponse.class);
        return paymentResponseResponseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payments/", produces = "application/json")
    public ResponseEntity<PaymentListResponse> getAllPaymentsList() {
        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<PaymentListResponse> paymentResponseResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, request, PaymentListResponse.class);
        return paymentResponseResponseEntity;
    }
}
