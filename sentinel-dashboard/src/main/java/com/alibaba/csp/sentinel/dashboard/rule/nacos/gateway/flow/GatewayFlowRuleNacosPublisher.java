package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
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
 * @date 2021-12-24 15:08
 */
@Component("gatewayFlowRuleNacosPublisher")
public class GatewayFlowRuleNacosPublisher extends AbstractRuleNacosPublisher<List<GatewayFlowRuleEntity>> {

    private Converter<List<GatewayFlowRuleEntity>, String> converter;

    @Autowired
    public GatewayFlowRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                         Converter<List<GatewayFlowRuleEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.GATEWAY_FLOW_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<GatewayFlowRuleEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
