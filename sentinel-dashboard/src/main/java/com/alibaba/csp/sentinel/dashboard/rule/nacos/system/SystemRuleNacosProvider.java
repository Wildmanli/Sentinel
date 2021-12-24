package com.alibaba.csp.sentinel.dashboard.rule.nacos.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
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
 * @date 2021-12-24 15:14
 */
@Component("systemRuleNacosProvider")
public class SystemRuleNacosProvider extends AbstractRuleNacosProvider<List<SystemRuleEntity>> {

    private Converter<String, List<SystemRuleEntity>> converter;

    @Autowired
    public SystemRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                   Converter<String, List<SystemRuleEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.SYS_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<SystemRuleEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
