package com.onyx.ad.search.index;

import com.alibaba.fastjson.JSON;

import com.onyx.ad.common.dump.DConstant;
import com.onyx.ad.common.dump.table.*;
import com.onyx.ad.search.handle.AdLevelDataHandler;
import com.onyx.ad.search.mysql.constant.OpType;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @DependsOn 控制bean加载顺序
 *
可能有些场景中，bean A 间接依赖 bean B。如Bean B应该需要更新一些全局缓存，可能通过单例模式实现且没有在spring容器注册，
bean A需要使用该缓存；因此，如果bean B没有准备好，bean A无法访问。

另一个场景中，bean A是事件发布者（或JMS发布者），bean B (或一些) 负责监听这些事件，典型的如观察者模式。我们不想B 错过任何事件，那么B需要首先被初始化。

简言之，有很多场景需要bean B应该被先于bean A被初始化，从而避免各种负面影响。我们可以在bean A上使用@DependsOn注解，
告诉容器bean B应该先被初始化。下面通过示例来说明。
 *
 */
@Component
@DependsOn("dataTable")
public class IndexFileLoader {

    /**
     * 这里的顺序非常重要,只能这么加载
     */
    @PostConstruct
    public void init() {

        List<String> adPlanStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_PLAN)
        );
        adPlanStrings.forEach(p -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(p, AdPlanTable.class),
                OpType.ADD
        ));

        List<String> adCreativeStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE)
        );
        adCreativeStrings.forEach(c -> AdLevelDataHandler.handleLevel2(
                JSON.parseObject(c, AdCreativeTable.class),
                OpType.ADD
        ));

        List<String> adUnitStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT)
        );
        adUnitStrings.forEach(u -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(u, AdUnitTable.class),
                OpType.ADD
        ));

        List<String> adCreativeUnitStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_CREATIVE_UNIT)
        );
        adCreativeUnitStrings.forEach(cu -> AdLevelDataHandler.handleLevel3(
                JSON.parseObject(cu, AdCreativeUnitTable.class),
                OpType.ADD
        ));

        List<String> adUnitDistrictStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_DISTRICT)
        );
        adUnitDistrictStrings.forEach(d -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(d, AdUnitDistrictTable.class),
                OpType.ADD
        ));

        List<String> adUnitItStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_IT)
        );
        adUnitItStrings.forEach(i -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(i, AdUnitItTable.class),
                OpType.ADD
        ));

        List<String> adUnitKeywordStrings = loadDumpData(
                String.format("%s%s",
                        DConstant.DATA_ROOT_DIR,
                        DConstant.AD_UNIT_KEYWORD)
        );
        adUnitKeywordStrings.forEach(k -> AdLevelDataHandler.handleLevel4(
                JSON.parseObject(k, AdUnitKeywordTable.class),
                OpType.ADD
        ));
    }

    private List<String> loadDumpData(String fileName) {

        try (BufferedReader br = Files.newBufferedReader(
                Paths.get(fileName)
        )) {
            return br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
