package com.example.fraud;

import com.rose.client.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isFraudulentCustomer=fraudCheckService.isFraudulentCustomer(customerId);
        return new FraudCheckResponse(false);
//        return new FraudCheckResponse()
    }
}
