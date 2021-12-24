package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
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
 * @date 2021-12-24 15:13
 */
@Component("paramRuleNacosPublisher")
public class ParamRuleNacosPublisher extends AbstractRuleNacosPublisher<List<ParamFlowRuleEntity>> {

    private Converter<List<ParamFlowRuleEntity>, String> converter;

    @Autowired
    public ParamRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                   Converter<List<ParamFlowRuleEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<ParamFlowRuleEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
