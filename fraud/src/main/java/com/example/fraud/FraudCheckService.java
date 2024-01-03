package com.example.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FraudCheckService {
    private final FraudCheckRepository fraudCheckRepository;

    public boolean isFraudulentCustomer(Integer customerId){
        fraudCheckRepository.save(
                FraudCCheckHistory.builder()
                        .customerId(customerId)
                        .isFraud(false)
                        .build()
        );
        return false;
    }

}
