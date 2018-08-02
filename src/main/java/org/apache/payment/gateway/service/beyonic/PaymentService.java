package org.apache.payment.gateway.service.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.response.EventsBaseResponse;
import org.apache.payment.gateway.dto.beyonic.response.PaymentResponse;
import org.apache.payment.gateway.repository.beyonic.PaymentRepository;
import org.apache.payment.gateway.utils.HttpHeaderUtil;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.apache.payment.gateway.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Sanyam Goel created on 24/7/18
 */
@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

//    public PaymentResponse getlistOfPayments() {
//        ResponseEntity<Object> paymentResponse = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), PaymentResponse.class);
//        EventsBaseResponse eventsResponse = Utility.convertModel(paymentResponse, EventsBaseResponse.class);
//        if(eventsResponse.getEventType().equalsIgnoreCase("created")){
//            CreatedEventResponse createdEventResponse = Utility.convertModel(paymentResponse, CreatedEventResponse.class);
////            created
//        }
//
//        return paymentResponse.getBody();
//    }

}
