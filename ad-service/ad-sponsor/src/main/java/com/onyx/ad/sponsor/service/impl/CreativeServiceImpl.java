package com.onyx.ad.sponsor.service.impl;

import com.onyx.ad.sponsor.dao.CreativeRepository;
import com.onyx.ad.sponsor.entity.AdCreative;
import com.onyx.ad.sponsor.service.ICreativeService;
import com.onyx.ad.sponsor.vo.CreativeRequest;
import com.onyx.ad.sponsor.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("creativeService")
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        AdCreative creative = creativeRepository.save(
                request.convertToEntity()
        );
        return new CreativeResponse(creative.getId(), creative.getName());
    }


}