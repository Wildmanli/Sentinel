package com.alibaba.csp.sentinel.dashboard.rule.nacos.auth;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
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
 * @date 2021-12-24 13:50
 */
@Component("authRuleNacosPublisher")
public class AuthorityRuleNacosPublisher extends AbstractRuleNacosPublisher<List<AuthorityRuleEntity>> {

    private final Converter<List<AuthorityRuleEntity>, String> converter;

    @Autowired
    public AuthorityRuleNacosPublisher(ConfigService configService, NacosProperties nacosProperties,
                                       Converter<List<AuthorityRuleEntity>, String> converter) {
        super(configService, nacosProperties, NacosConfigUtil.AUTH_DATA_ID_POSTFIX);
        this.converter = converter;
    }

    @Override
    protected String convert(List<AuthorityRuleEntity> rules) {
        return converter.convert(null == rules ? Collections.emptyList() : rules);
    }
}
