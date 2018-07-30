package org.apache.payment.gateway.service.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.response.WebHookCollectionReceivedResponse;
import org.apache.payment.gateway.dto.beyonic.response.WebHookCollectionRequestStatusResponse;
import org.apache.payment.gateway.dto.beyonic.response.WebHookPaymentStatusResponse;
import org.apache.payment.gateway.repository.beyonic.WebHookRepository;
import org.apache.payment.gateway.utils.HttpHeaderUtil;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Sanyam Goel created on 29/7/18
 */
@Service
public class WebHookService {

    @Autowired
    WebHookRepository webHookRepository;

    public String getPaymentStatusChangedEvent(WebHookPaymentStatusResponse webHookPaymentStatusResponse) {
        System.out.println(webHookPaymentStatusResponse.toString());
        return webHookPaymentStatusResponse.toString();

//        ResponseEntity<WebHookPaymentStatusResponse> eventResponseObject = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), WebHookPaymentStatusResponse.class);
//        eventResponseObject.getBody();
    }

    public void getCollectionReceivedEvent(WebHookCollectionReceivedResponse webHookCollectionReceivedResponse) {
//        ResponseEntity<WebHookCollectionReceivedResponse> eventResponseObject = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), WebHookCollectionReceivedResponse.class);
//        eventResponseObject.getBody();
    }

    public String getCollectionRequestStatusChangedEvent(WebHookCollectionRequestStatusResponse webHookCollectionRequestStatusResponse) {
        return webHookCollectionRequestStatusResponse.toString();

//        ResponseEntity<WebHookCollectionRequestStatusResponse> eventResponseObject = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), WebHookCollectionRequestStatusResponse.class);
//        eventResponseObject.getBody();
    }
}
