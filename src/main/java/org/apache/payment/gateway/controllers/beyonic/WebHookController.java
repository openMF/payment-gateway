package org.apache.payment.gateway.controllers.beyonic;

import org.apache.payment.gateway.dto.beyonic.response.WebHookCollectionReceivedResponse;
import org.apache.payment.gateway.dto.beyonic.response.WebHookCollectionRequestStatusResponse;
import org.apache.payment.gateway.dto.beyonic.response.WebHookPaymentStatusResponse;
import org.apache.payment.gateway.service.beyonic.WebHookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sanyam Goel created on 28/7/18
 */

@RequestMapping(value = "/payment-gateway/api/v1")
@RestController
public class WebHookController {

    @Autowired
    WebHookService webHookService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/webhook-payment-status")
    public void getPaymentStatusEvent(
            @RequestBody WebHookPaymentStatusResponse webHookPaymentStatusResponse
    ) {
        webHookService.getPaymentStatusChangedEvent(webHookPaymentStatusResponse);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook-collection")
    public void getCollectionReceivedEvent(
            @RequestBody WebHookCollectionReceivedResponse webHookCollectionReceivedResponse
    ) {
        webHookService.getCollectionReceivedEvent(webHookCollectionReceivedResponse);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/webhook-collection-request")
    public void getCollectionRequestStatusEvent(
            @RequestBody WebHookCollectionRequestStatusResponse webHookCollectionRequestStatusResponse
    ) {
        webHookService.getCollectionRequestStatusChangedEvent(webHookCollectionRequestStatusResponse);
    }

}
