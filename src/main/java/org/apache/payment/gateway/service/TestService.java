package org.apache.payment.gateway.service;

import org.apache.payment.gateway.config.aspect.Loggable;
import org.apache.payment.gateway.utils.Utility;
import org.springframework.stereotype.Service;

/**
 * @author Rahul Goel created on 2/6/18
 */

@Service
public class TestService {

    @Loggable
    public String testServiceMethod(String testParam){
        if(Utility.isNullOrBlank(testParam)){
            return "Everything looks cool!!";
        }else {
            return testParam;
        }
    }

}
