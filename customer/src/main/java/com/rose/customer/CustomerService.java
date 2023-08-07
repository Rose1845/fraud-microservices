package com.rose.customer;


import com.rose.customer.client.FraudCheckResponse;
import com.rose.customer.client.FraudClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    @Autowired
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
    }

    public void registerCustomer(CustomerRequest customerRequest) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customerRequest.getEmail());
        if(existingCustomer.isPresent()){
            throw new IllegalStateException("customer already exists");
        }

        Customer customer = Customer.builder()

                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();
        // todo: check if email is valid
        // todo: check if email no taken
        // todo: store customer in db
       customerRepository.saveAndFlush(customer);
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8082/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class, customer.getId());
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        log.info("fraud check for ");
        if(fraudCheckResponse.isFraudster){
            throw new IllegalStateException("fraudster");
        }
    }
}
