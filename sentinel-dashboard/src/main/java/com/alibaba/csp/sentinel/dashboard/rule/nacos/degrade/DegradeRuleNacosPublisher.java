package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.AbstractRuleNacosPublisher;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author lihao
 * @date 2021-12-24 14:45
 */
@Component("degradeRuleNacosPublisher")
public class DegradeRuleNacosPublisher extends AbstractRuleNacosPublisher<List<DegradeRuleEntity>> {

    private final Converter<List<DegradeRuleEntity>, String> converter;

    @Autowired
    public DegradeRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                       Converter<List<DegradeRuleEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<DegradeRuleEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
