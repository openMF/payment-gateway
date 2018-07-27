package org.apache.payment.gateway.controllers.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.response.CollectionNotifyListResponse;
import org.apache.payment.gateway.dto.beyonic.response.CollectionNotifyResponse;
import org.apache.payment.gateway.utils.HttpHeaderUtil;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanyam Goel created on 25/7/18
 */
@RequestMapping(value = "/payment-gateway/api/v1/beyonic/")
@RestController
public class CollectionController {

    @RequestMapping(method = RequestMethod.GET, value = "/collection/{id}", produces = "application/json")
    public ResponseEntity<CollectionNotifyResponse> getCollectionNotificationById(
            @PathVariable("id") long id
    ) {
        ResponseEntity<CollectionNotifyResponse> collectionNotifyResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.COLLECTION_REQUEST_API_ENDPOINT + "/" + id, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), CollectionNotifyResponse.class);
        return collectionNotifyResponseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/collection/", produces = "application/json")
    public ResponseEntity<CollectionNotifyListResponse> getCollectionNotificationList() {
        ResponseEntity<CollectionNotifyListResponse> collectionNotifyResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.COLLECTION_REQUEST_API_ENDPOINT, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), CollectionNotifyListResponse.class);
        return collectionNotifyResponseEntity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/collection/filter", produces = "application/json")
    public ResponseEntity<CollectionNotifyResponse> getPayments(
            @RequestParam(required = false, name = "amount") double amount,
            @RequestParam(required = false, name = "currency") String currency,
            @RequestParam(required = false, name = "collection") long collection,
            @RequestParam(required = false, name = "phonenumber") String phonenumber,
            @RequestParam(required = false, name = "remote_transaction_id") String remote_transaction_id,
            @RequestParam(required = false, name = "created_after") String created_after,
            @RequestParam(required = false, name = "created_before") String created_before
    ) {
        String transactionalUrl = BeyonicConstants.PAYMENT_API_ENDPOINT;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionalUrl);
        Map<String, String> params = new HashMap<String, String>();
//        params.put("")
        ResponseEntity<CollectionNotifyResponse> collectionNotifyResponseEntity = RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.PAYMENT_API_ENDPOINT, HttpMethod.GET, HttpHeaderUtil.getHttpEntityInstance(), CollectionNotifyResponse.class);
        return collectionNotifyResponseEntity;
    }

}
