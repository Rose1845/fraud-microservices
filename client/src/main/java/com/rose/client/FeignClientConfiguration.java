package com.rose.client;

import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = """
        """)
public class FeignClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        // Set Feign client logger level (e.g., FULL to log all requests and responses)
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        // Define a custom error decoder if needed
        return new ErrorDecoder.Default();
    }
}
