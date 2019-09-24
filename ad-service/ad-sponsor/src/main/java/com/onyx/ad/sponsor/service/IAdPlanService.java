package com.onyx.ad.sponsor.service;

import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.sponsor.entity.AdPlan;
import com.onyx.ad.sponsor.vo.AdPlanGetRequest;
import com.onyx.ad.sponsor.vo.AdPlanRequest;
import com.onyx.ad.sponsor.vo.AdPlanResponse;

import java.util.List;

/**
 * @author zk
 * @Description:
 * @date 2019-09-24 15:15
 */
public interface IAdPlanService {

    /**
     * <h2>创建推广计划</h2>
     * */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>获取一个用户的推广计划</h2>
     * */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    /**
     * <h2>更新推广计划</h2>
     * */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
     * <h2>删除推广计划</h2>
     * */
    void deleteAdPlan(AdPlanRequest request) throws AdException;

}
