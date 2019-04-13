package com.prateek.projects.microservices.DNSService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviceDnsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDnsApplication.class, args);
    }
}
