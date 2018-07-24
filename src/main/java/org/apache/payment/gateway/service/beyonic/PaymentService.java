package org.apache.payment.gateway.service.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.response.PaymentListResponse;
import org.apache.payment.gateway.dto.beyonic.response.PaymentResponse;
import org.apache.payment.gateway.repository.beyonic.PaymentRepository;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Sanyam Goel created on 24/7/18
 */
@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

//    public PaymentResponse getListOfPayments() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Token ");
//        HttpEntity request = new HttpEntity(headers);
//
//
////        List<PaymentListResponse> paymentListResponse = paymentRepository.getList(PaymentListResponse.class);
////        PaymentResponse paymentListResponse = RestTemplateUtil.getRestTemplateInstance().getForObject(BeyonicConstants.PAYMENT_API_ENDPOINT + "Token " + BeyonicConstants.API_TOKEN, PaymentResponse.class);
////        PaymentResponse paymentResponse = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, request, PaymentResponse.class);
////        return paymentResponse;
//    }
}
