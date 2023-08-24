package com.railway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
//@EnableAutoConfiguration
@EnableSpringDataWebSupport
@EnableAspectJAutoProxy

public class RailwaySystemApplication {
    public static void main(String[] args) {
            SpringApplication.run(RailwaySystemApplication.class, args);
    }
}