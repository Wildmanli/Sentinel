package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
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
 * @date 2021-12-24 15:05
 */
@Component("gatewayApiRuleNacosPublisher")
public class GatewayApiRuleNacosPublisher extends AbstractRuleNacosPublisher<List<ApiDefinitionEntity>> {

    private Converter<List<ApiDefinitionEntity>, String> converter;

    @Autowired
    public GatewayApiRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                        Converter<List<ApiDefinitionEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<ApiDefinitionEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
