package com.onyx.ad.search.controller;

import com.onyx.ad.common.annotation.IgnoreResponseAdvice;
import com.onyx.ad.common.vo.CommonResponse;
import com.onyx.ad.search.client.AdPlan;
import com.onyx.ad.search.client.AdPlanGetRequest;
import com.onyx.ad.search.client.SponsorClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SponsorClient sponsorClient;


    @GetMapping("test1")
    @IgnoreResponseAdvice
    public CommonResponse<List<AdPlan>> test1(@RequestBody AdPlanGetRequest request){

        //restTemplate
        ResponseEntity<CommonResponse> entity = restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPlan", request, CommonResponse.class);
        return entity.getBody();

        //feign调用
        /*CommonResponse<List<AdPlan>> plans = sponsorClient.getAdPlans(request);
        return plans;*/

    }


    @GetMapping("test2")
    public String test2(){
        return "success";
    }



}
