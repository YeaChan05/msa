 package org.yechan.gatewayspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
 
 @SpringBootApplication
public class GatewaySpringCloudApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GatewaySpringCloudApplication.class, args);
    }
    @Bean
    public HttpExchangeRepository httpExchangeRepository(){
        return new InMemoryHttpExchangeRepository();
    }
    
}
