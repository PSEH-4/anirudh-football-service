package com.ps.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@EnableEurekaClient
@EnableCircuitBreaker
@RefreshScope
@SpringBootApplication
@Slf4j
public class FootballStandingMsApplication {

	
    public static void main(String[] args) {
        SpringApplication.run(FootballStandingMsApplication.class, args);
    }
}
