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

    @RequestMapping(method = RequestMethod.GET, value = "/payments/{id}", produces = "application/json")
    public ResponseEntity<PaymentResponse> getAllPaymentsList(
            @PathVariable("id") long id
    ) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<PaymentResponse> paymentResponseResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT + id, HttpMethod.GET, request, PaymentResponse.class);

//        PaymentResponse vendors = paymentService.getListOfPayments();
        return paymentResponseResponseEntity;
    }
}
