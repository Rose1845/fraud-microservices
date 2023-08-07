package com.rose.customer;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController{
   private final CustomerService customerService;
   @PostMapping
  public void registerCustomer(@RequestBody CustomerRequest customerRequest) {
//       Logger logger = LoggerFactory.getLogger(customerRequest);
       customerService.registerCustomer(customerRequest);
       log.info("new customer registration");
//       log.info("new customer registration",{});

   }
//       @PostMapping
//       public ResponseEntity<String> registerCustomer(@RequestBody CustomerRequest customerRequest) {
//           try {
//               customerService.registerCustomer(customerRequest);
//               log.info("New customer registration: {}", customerRequest.getEmail());
//               return ResponseEntity.ok("Customer registration successful");
//           } catch (IllegalStateException e) {
//               // Handle the case when customer already exists or other business rules violation
//               return ResponseEntity.status(HttpStatus.CONFLICT).body("Customer already exists");
//           } catch (Exception e) {
//               // Handle unexpected errors or service failures
//               log.error("Error occurred during customer registration: {}", e.getMessage());
//               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//           }
//       }

}
