package org.apache.payment.gateway.utils;

import org.apache.payment.gateway.constants.beyonic.BeyonicConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * @author Sanyam Goel created on 27/7/18
 */
public class HttpHeaderUtil {

    private static HttpHeaders headers = new HttpHeaders();
    private static HttpEntity requestEntity;

    public static HttpEntity getHttpEntityInstance() {
        headers.set("Authorization", "Token " + BeyonicConstants.API_TOKEN);
        return requestEntity = new HttpEntity(headers);
    }
}
