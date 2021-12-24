package com.alibaba.csp.sentinel.dashboard.rule.nacos.auth;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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
 * @date 2021-12-24 13:30
 */
@Component("authRuleNacosProvider")
public class AuthorityRuleNacosProvider extends AbstractRuleNacosProvider<List<AuthorityRuleEntity>> {

    private final Converter<String, List<AuthorityRuleEntity>> converter;

    @Autowired
    public AuthorityRuleNacosProvider(ConfigService configService, NacosProperties nacosProperties,
                                      Converter<String, List<AuthorityRuleEntity>> converter) {
        super(configService, nacosProperties, NacosConfigUtil.AUTH_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected List<AuthorityRuleEntity> convert(String rules) {
        return StringUtil.isEmpty(rules) ? new ArrayList<>() : converter.convert(rules);
    }
}
