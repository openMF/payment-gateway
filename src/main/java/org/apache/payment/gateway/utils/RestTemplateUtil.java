package org.apache.payment.gateway.utils;

import org.springframework.web.client.RestTemplate;

public class RestTemplateUtil {

    private static RestTemplate restTemplate = null;

    public static RestTemplate getRestTemplateInstance() {
        if (restTemplate != null) {
            return restTemplate = new RestTemplate();
        } else {
            synchronized (RestTemplateUtil.class) {
                restTemplate = new RestTemplate();
                return restTemplate;
            }
        }
    }
}
