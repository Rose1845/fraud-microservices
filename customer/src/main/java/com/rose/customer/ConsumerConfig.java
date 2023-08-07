package com.rose.customer;

import com.rose.customer.client.FraudClient;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ConsumerConfig {
    private final  LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;

     @Autowired
    public ConsumerConfig(LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction) {
        this.loadBalancedExchangeFilterFunction = loadBalancedExchangeFilterFunction;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public WebClient fraudWebClient(){
//        return WebClient.builder()
//                .baseUrl("http://fraud")
//                .filter(loadBalancedExchangeFilterFunction)
//                .build();
//    }
//
//    @Bean
//    public FraudClient fraudClient(){
//        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(fraudWebClient())).build();
//        return httpServiceProxyFactory.createClient(FraudClient.class);
//    }
@Bean
public WebClient.Builder webClientBuilder() {
    return WebClient.builder()
            .baseUrl("http://fraud")
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML)
//            .defaultHeader(HttpHeaders.ACCEPT, "*/*")
            .filter(loadBalancedExchangeFilterFunction);
}

    @Bean
    public FraudClient fraudClient(WebClient.Builder webClientBuilder) {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClientBuilder.build())).build();
        return httpServiceProxyFactory.createClient(FraudClient.class);
    }
}
