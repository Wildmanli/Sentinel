package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.AbstractRuleNacosProvider;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosConfigUtil;
import com.alibaba.csp.sentinel.dashboard.rule.nacos.NacosProperties;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihao
 * @date 2021-12-24 15:06
 */
@Component("gatewayFlowRuleNacosProvider")
public class GatewayFlowRuleNacosProvider extends AbstractRuleNacosProvider<List<GatewayFlowRuleEntity>> {

    private Converter<String, List<GatewayFlowRuleEntity>> converter;

    @Autowired
    public GatewayFlowRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                        Converter<String, List<GatewayFlowRuleEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.GATEWAY_FLOW_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<GatewayFlowRuleEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
