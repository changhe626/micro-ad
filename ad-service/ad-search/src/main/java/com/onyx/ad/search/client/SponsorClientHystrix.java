package com.onyx.ad.search.client;

import com.onyx.ad.common.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 熔断器需要实现SponsorClient 接口
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    /**
     * 参数和返回一致, 方法名不同
     */
    @Override
    public CommonResponse<List<AdPlan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1,
                "eureka-client-ad-sponsor error");
    }
}
