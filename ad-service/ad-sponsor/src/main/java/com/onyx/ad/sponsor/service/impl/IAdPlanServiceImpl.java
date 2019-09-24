package com.onyx.ad.sponsor.service.impl;

import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.sponsor.constant.CommonStatus;
import com.onyx.ad.sponsor.constant.Constants;
import com.onyx.ad.sponsor.dao.AdPlanRepository;
import com.onyx.ad.sponsor.dao.AdUserRepository;
import com.onyx.ad.sponsor.entity.AdPlan;
import com.onyx.ad.sponsor.entity.AdUser;
import com.onyx.ad.sponsor.service.IAdPlanService;
import com.onyx.ad.sponsor.util.CommonUtils;
import com.onyx.ad.sponsor.vo.AdPlanGetRequest;
import com.onyx.ad.sponsor.vo.AdPlanRequest;
import com.onyx.ad.sponsor.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author zk
 * @Description:
 * @date 2019-09-24 15:15
 */

@Service("adPlanService")
public class IAdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdPlanRepository adPlanRepository;
    @Autowired
    private AdUserRepository adUserRepository;


    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request)
            throws AdException {

        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 确保关联的 User 是存在的
        Optional<AdUser> adUser =
                adUserRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = adPlanRepository.findByUserIdAndPlanName(
                request.getUserId(), request.getPlanName()
        );
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        AdPlan newAdPlan = adPlanRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );
        return new AdPlanResponse(newAdPlan.getId(),
                newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)
            throws AdException {

        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return adPlanRepository.findAllByIdInAndUserId(
                request.getIds(), request.getUserId()
        );
    }

    /**
     * 可以更新部分字段
     * @param request
     * @return
     * @throws AdException
     */
    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request)
            throws AdException {

        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null) {
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }
        if (request.getEndDate() != null) {
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }

        plan.setUpdateTime(new Date());
        plan = adPlanRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }


    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getCode());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
    }
}
