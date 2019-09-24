package com.onyx.ad.sponsor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zk
 * @Description:
 * @date 2019-09-24 14:29
 */
@SpringBootApplication
@EnableEurekaClient   //注册中心
@EnableFeignClients   //调用其他微服务,只是为了监控
@EnableCircuitBreaker   //断路器
public class SponsorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SponsorApplication.class, args);
    }

}
