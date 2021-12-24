package com.alibaba.csp.sentinel.dashboard.rule.nacos.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
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
 * @date 2021-12-24 15:11
 */
@Component("paramRuleNacosProvider")
public class ParamRuleNacosProvider extends AbstractRuleNacosProvider<List<ParamFlowRuleEntity>> {

    private Converter<String, List<ParamFlowRuleEntity>> converter;

    @Autowired
    public ParamRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                  Converter<String, List<ParamFlowRuleEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<ParamFlowRuleEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
