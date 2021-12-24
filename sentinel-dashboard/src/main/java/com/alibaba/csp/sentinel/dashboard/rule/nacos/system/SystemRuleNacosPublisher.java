package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
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
 * @date 2021-12-24 15:15
 */
@Component("systemRuleNacosPublisher")
public class SystemRuleNacosPublisher extends AbstractRuleNacosPublisher<List<SystemRuleEntity>> {

    private Converter<List<SystemRuleEntity>, String> converter;

    @Autowired
    public SystemRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                    Converter<List<SystemRuleEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.SYS_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<SystemRuleEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
