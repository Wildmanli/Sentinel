package com.alibaba.csp.sentinel.dashboard.rule.nacos.degrade;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
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
 * @date 2021-12-24 14:40
 */
@Component("degradeRuleNacosProvider")
public class DegradeRuleNacosProvider extends AbstractRuleNacosProvider<List<DegradeRuleEntity>> {

    private final Converter<String, List<DegradeRuleEntity>> converter;

    @Autowired
    public DegradeRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                    Converter<String, List<DegradeRuleEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<DegradeRuleEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
