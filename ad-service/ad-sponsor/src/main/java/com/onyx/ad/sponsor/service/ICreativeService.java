package com.onyx.ad.sponsor.service;

import com.onyx.ad.sponsor.vo.CreativeRequest;
import com.onyx.ad.sponsor.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}