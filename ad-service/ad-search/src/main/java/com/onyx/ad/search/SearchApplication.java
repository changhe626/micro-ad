package com.onyx.ad.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 使用
 * @SpringCloudApplication  可以少很多注解
 */

@SpringBootApplication
@EnableEurekaClient  //客户端注册
@EnableFeignClients//feign调用其他服务
@EnableHystrix   //断路器
@EnableCircuitBreaker  //断路器
@EnableHystrixDashboard //hystrix的监控
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

}
