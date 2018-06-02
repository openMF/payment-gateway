package payment_gateway.service;

import org.springframework.stereotype.Service;
import payment_gateway.config.aspect.Loggable;
import payment_gateway.utils.Utility;

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
