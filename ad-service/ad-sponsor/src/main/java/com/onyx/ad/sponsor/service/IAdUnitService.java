package com.onyx.ad.sponsor.service;

import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.sponsor.vo.AdUnitDistrictRequest;
import com.onyx.ad.sponsor.vo.AdUnitDistrictResponse;
import com.onyx.ad.sponsor.vo.AdUnitItRequest;
import com.onyx.ad.sponsor.vo.AdUnitItResponse;
import com.onyx.ad.sponsor.vo.AdUnitKeywordRequest;
import com.onyx.ad.sponsor.vo.AdUnitKeywordResponse;
import com.onyx.ad.sponsor.vo.AdUnitRequest;
import com.onyx.ad.sponsor.vo.AdUnitResponse;
import com.onyx.ad.sponsor.vo.CreativeUnitRequest;
import com.onyx.ad.sponsor.vo.CreativeUnitResponse;

public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request)
            throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request)
            throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
            throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request)
            throws AdException;
}