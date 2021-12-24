package com.alibaba.csp.sentinel.dashboard.rule.nacos.gateway.api;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
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
 * @date 2021-12-24 15:03
 */
@Component("gatewayApiRuleNacosProvider")
public class GatewayApiRuleNacosProvider extends AbstractRuleNacosProvider<List<ApiDefinitionEntity>> {

    private Converter<String, List<ApiDefinitionEntity>> converter;

    @Autowired
    public GatewayApiRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                       Converter<String, List<ApiDefinitionEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.GATEWAY_API_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<ApiDefinitionEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
