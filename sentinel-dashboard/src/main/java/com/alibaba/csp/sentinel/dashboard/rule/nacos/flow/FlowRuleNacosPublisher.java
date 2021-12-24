package com.alibaba.csp.sentinel.dashboard.rule.nacos.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
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
 * @date 2021-12-24 14:58
 */
@Component("flowRuleNacosPublisher")
public class FlowRuleNacosPublisher extends AbstractRuleNacosPublisher<List<FlowRuleEntity>> {

    private Converter<List<FlowRuleEntity>, String> converter;

    @Autowired
    public FlowRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                  Converter<List<FlowRuleEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.FLOW_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<FlowRuleEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
