package com.restful.spring.boot.restful_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class RestfulSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulSpringBootApplication.class, args);
    }

}
