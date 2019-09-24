package com.onyx.ad.search.index.addplan;

import com.onyx.ad.search.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AdPlanIndex implements IndexAware<Long, AdPlanObject> {

    /**
     * 考虑到并发的情况
     */
    private static ConcurrentHashMap<Long, AdPlanObject> map = new ConcurrentHashMap<>();


    @Override
    public AdPlanObject get(Long key) {
        return map.get(key);
    }


    @Override
    public void add(Long key, AdPlanObject value) {
        log.info("before delete: {}", map);
        map.put(key, value);
        log.info("before delete: {}", map);
    }


    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("before update: {}", map);
        AdPlanObject oldObject = map.get(key);
        if (null == oldObject) {
            map.put(key, value);
        } else {
            oldObject.update(value);
        }
        log.info("after update: {}", map);
    }


    @Override
    public void delete(Long key, AdPlanObject value) {
        log.info("before delete: {}", map);
        map.remove(key, value);
        log.info("before delete: {}", map);
    }


    @Override
    public void delete(Long key) {
        log.info("before delete: {}", map);
        map.remove(key);
        log.info("before delete: {}", map);
    }


}
