package com.onyx.ad.search.index.keyword;


import com.onyx.ad.common.exception.AdException;
import com.onyx.ad.search.index.IndexAware;
import com.onyx.ad.search.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


@Slf4j
@Component
public class UnitKeywordIndex implements IndexAware<String, Set<Long>> {

    private static Map<String, Set<Long>> keywordUnitMap = new ConcurrentHashMap<>();
    private static Map<Long, Set<String>> unitKeywordMap = new ConcurrentHashMap<>();


    @Override
    public Set<Long> get(String key) {
        if (StringUtils.isEmpty(key)) {
            return Collections.emptySet();
        }
        Set<Long> result = keywordUnitMap.get(key);
        if (result == null) {
            return Collections.emptySet();
        }
        return result;
    }


    @Override
    public void add(String key, Set<Long> value) {

        log.info("UnitKeywordIndex, before add: {}", unitKeywordMap);

        Set<Long> unitIdSet = CommonUtils.getorCreate(
                key, keywordUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIdSet.addAll(value);

        for (Long unitId : value) {
            Set<String> keywordSet = CommonUtils.getorCreate(
                    unitId, unitKeywordMap,
                    ConcurrentSkipListSet::new
            );
            keywordSet.add(key);
        }
        log.info("UnitKeywordIndex, after add: {}", unitKeywordMap);
    }


    @Override
    public void update(String key, Set<Long> value) {
        log.error("keyword index can not support update");
        throw new AdException("支持更新操作");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitKeywordIndex, before delete: {}", unitKeywordMap);
        Set<Long> unitIds = CommonUtils.getorCreate(
                key, keywordUnitMap,
                ConcurrentSkipListSet::new
        );
        unitIds.removeAll(value);

        for (Long unitId : value) {
            Set<String> keywordSet = CommonUtils.getorCreate(
                    unitId, unitKeywordMap,
                    ConcurrentSkipListSet::new
            );
            keywordSet.remove(key);
        }
        log.info("UnitKeywordIndex, after delete: {}", unitKeywordMap);
    }

    @Override
    public void delete(String key) {
        throw new AdException("支持此种删除操作,请使用delete(String key, Set<Long> value)方法");
    }

    public boolean match(Long unitId, List<String> keywords) {

        if (unitKeywordMap.containsKey(unitId)
                && CollectionUtils.isNotEmpty(unitKeywordMap.get(unitId))) {

            Set<String> unitKeywords = unitKeywordMap.get(unitId);
            //子集
            return CollectionUtils.isSubCollection(keywords, unitKeywords);
        }

        return false;
    }
}
