package com.onyx.ad.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 这里是EnableZuulProxy   不是 @EnableZuulServer 注解, 记住了
 */
//@SpringCloudApplication
//@EnableZuulServer
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableCircuitBreaker
public class ZuulServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApp.class,args);
    }

}
