package payment_gateway.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rahul Goel created on 2/6/18
 */

@RequestMapping(value = "/payment-gateway/api/v1/")
@RestController
public class TestController {
    @RequestMapping(method = RequestMethod.GET, value = "/test", produces = "application/json")
    public ResponseEntity<Object> getTestEndpoint(){
        return new ResponseEntity<>("Everything looks cool!!", HttpStatus.OK);
    }
}