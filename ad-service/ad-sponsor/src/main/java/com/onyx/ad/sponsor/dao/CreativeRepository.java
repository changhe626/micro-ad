package com.onyx.ad.sponsor.dao;

import com.onyx.ad.sponsor.entity.AdCreative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreativeRepository extends JpaRepository<AdCreative, Long> {

}