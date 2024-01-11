package com.example.zuulserver.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.impl.nio.ServerHttp1IOEventHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security){
        security.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("/eureka/**").permitAll().anyExchange().authenticated()).oauth2ResourceServer((oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults())));
        return   security.build();

    }
}
