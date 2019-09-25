package com.onyx.ad.gateway2.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author zk
 * @Description:
 * @date 2019-09-25 12:16
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> testFunRouterFunction() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(
                RequestPredicates.path("/test"), request -> ServerResponse.ok()
                        .body(BodyInserters.fromObject("I am testing")));
        return route;
    }


    /**
     * 路由规则
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/test/custom").uri("http://ww.baidu.com"))
                .route(r -> r.path("/ad-search/**").uri("lb://EUREKA-CLIENT-AD-SEARCH"))
                .route(r->r.path("/ad-sponsor/**").uri("lb://EUREKA-CLIENT-AD-SPONSOR"))
                .build();
    }


}
