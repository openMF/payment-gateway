package org.apache.payment.gateway.controllers.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.response.PaymentListResponse;
import org.apache.payment.gateway.dto.beyonic.response.PaymentResponse;
import org.apache.payment.gateway.service.beyonic.PaymentService;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanyam Goel created on 24/7/18
 */
@RequestMapping(value = "/payment-gateway/api/v1/beyonic/")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    HttpHeaders headers = new HttpHeaders();
    HttpEntity requestEntity;

    private void HttpHeaderReturn() {
        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
        requestEntity = new HttpEntity(headers);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payments/{id}", produces = "application/json")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable("id") long id
    ) {
//        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
//        HttpEntity request = new HttpEntity(headers);
        HttpHeaderReturn();
        ResponseEntity<PaymentResponse> paymentResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT + "/" + id, HttpMethod.GET, requestEntity, PaymentResponse.class);
        return paymentResponseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payments/", produces = "application/json")
    public ResponseEntity<PaymentListResponse> getAllPaymentsList() {
//        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
//        HttpEntity request = new HttpEntity(headers);
//        PaymentListResponse paymentListResponse = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, request, PaymentListResponse.class);
        HttpHeaderReturn();
        ResponseEntity<PaymentListResponse> paymentResponseListEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, requestEntity, PaymentListResponse.class);
//        return new ResponseEntity<PaymentListResponse>(paymentResponseResponseEntity, HttpStatus.OK);
        return paymentResponseListEntity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/payments/filter", produces = "application/json")
    public ResponseEntity<PaymentResponse> getPayments(
            @RequestParam(required = false, name = "amount") double amount,
            @RequestParam(required = false, name = "currency") String currency,
            @RequestParam(required = false, name = "payment_type") String payment_type,
            @RequestParam(required = false, name = "created_after") String created_after,
            @RequestParam(required = false, name = "created_before") String created_before
    ) {
        String transactionalUrl = BeyonicConstants.PAYMENT_API_ENDPOINT;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionalUrl);
        Map<String, String> params = new HashMap<String, String>();
//        params.put("")
        HttpHeaderReturn();
        ResponseEntity<PaymentResponse> paymentResponseResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, requestEntity, PaymentResponse.class);
        return paymentResponseResponseEntity;
    }
}
