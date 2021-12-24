package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * @author lihao
 * @date 2021-12-24 13:56
 */
public abstract class AbstractRuleNacosProvider<T> implements DynamicRuleProvider<T> {

    private final ConfigService configService;

    private final NacosProperties nacosProperties;

    private final String dataIdPostfix;

    public AbstractRuleNacosProvider(ConfigService configService,
                                     NacosProperties nacosProperties, String dataIdPostfix) {
        this.configService = configService;
        this.nacosProperties = nacosProperties;
        this.dataIdPostfix = dataIdPostfix;
    }

    @Override
    public T getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + dataIdPostfix,
                nacosProperties.getGroupId(), nacosProperties.getTimeoutMs());
        return convert(rules);
    }

    /**
     * Nacos 规则数据转化
     *
     * @param rules 规则数据
     * @return T
     */
    protected abstract T convert(String rules);
}
