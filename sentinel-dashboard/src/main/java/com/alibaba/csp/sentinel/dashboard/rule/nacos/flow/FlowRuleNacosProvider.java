package com.alibaba.csp.sentinel.dashboard.rule.nacos.flow;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
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
 * @date 2021-12-24 14:47
 */
@Component("flowRuleNacosProvider")
public class FlowRuleNacosProvider extends AbstractRuleNacosProvider<List<FlowRuleEntity>> {

    private Converter<String, List<FlowRuleEntity>> converter;

    @Autowired
    public FlowRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                 Converter<String, List<FlowRuleEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.FLOW_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<FlowRuleEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
