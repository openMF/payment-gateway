package org.apache.payment.gateway.service.beyonic;

import org.apache.payment.gateway.dto.beyonic.request.CollectionNotifyRequest;
import org.apache.payment.gateway.repository.beyonic.CollectionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sanyam Goel created on 29/7/18
 */
@Service
public class CollectionRequestService {

    @Autowired
    CollectionRequestRepository collectionRequestRepository;

    public CollectionNotifyRequest createCollectionRequest() {
//        create collectionRequestObject from transaction received.
        return null;
    }
}
