package org.apache.payment.gateway.service.beyonic;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.apache.payment.gateway.dto.beyonic.request.CollectionNotifyRequest;
import org.apache.payment.gateway.dto.beyonic.response.CollectionNotifyResponse;
import org.apache.payment.gateway.dto.request.TransactionRequest;
import org.apache.payment.gateway.utils.RestTemplateUtil;
import org.apache.payment.gateway.utils.exceptions.PgBaseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Sanyam Goel created on 29/7/18
 */

@Component
public class BeyonicService {

    public CollectionNotifyResponse createCollectionRequest(TransactionRequest transactionRequest){
        CollectionNotifyRequest collectionNotifyRequest = this.prepareCollectionNotifyRequest(transactionRequest);
        try {
            return RestTemplateUtil.getRestTemplateInstance().exchange(BeyonicConstants.COLLECTION_REQUEST_API_ENDPOINT,
                    HttpMethod.POST,
                    new HttpEntity(collectionNotifyRequest, this.getHeadersForBeyonic()),
                    CollectionNotifyResponse.class
            ).getBody();
            }catch (Exception e){
            System.out.println("aadas");
            throw new PgBaseException(e.getMessage());
        }
    }

    private CollectionNotifyRequest prepareCollectionNotifyRequest(TransactionRequest transactionRequest){
        CollectionNotifyRequest collectionNotifyRequest = new CollectionNotifyRequest();
        collectionNotifyRequest.setPhoneNumber(transactionRequest.getPhoneNumber());
        collectionNotifyRequest.setFirstName(transactionRequest.getFirstName());
        collectionNotifyRequest.setLastName(transactionRequest.getLastName());
        collectionNotifyRequest.setAmount(transactionRequest.getAmount());
        collectionNotifyRequest.setCurrency(transactionRequest.getCurrency());
        collectionNotifyRequest.setReason(transactionRequest.getReason());
        collectionNotifyRequest.setMetaData(transactionRequest.getMetaData());
//        collectionNotifyRequest.setSuccessMessage(transactionRequest.getSuccessMessage());
        collectionNotifyRequest.setSendInstructions(true);
//        collectionNotifyRequest.setInstructions();
//        collectionNotifyRequest.setExpiryDate();
        return collectionNotifyRequest;
    }

    private MultiValueMap<String, String> getHeadersForBeyonic(){
        MultiValueMap<String, String > headers = new LinkedMultiValueMap<>();
        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
        return headers;
    }

}
