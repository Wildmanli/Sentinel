package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * @author lihao
 * @date 2021-12-24 13:56
 */
public abstract class AbstractRuleNacosPublisher<T> implements DynamicRulePublisher<T> {

    private final ConfigService configService;

    private final NacosProperties nacosProperties;

    private final String dataIdPostfix;

    public AbstractRuleNacosPublisher(ConfigService configService,
                                      NacosProperties nacosProperties, String dataIdPostfix) {
        this.configService = configService;
        this.nacosProperties = nacosProperties;
        this.dataIdPostfix = dataIdPostfix;
    }

    @Override
    public void publish(String app, T rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + dataIdPostfix, nacosProperties.getGroupId(), convert(rules));
    }

    /**
     * Nacos 规则数据转化
     *
     * @param rules 规则数据
     * @return String
     */
    protected abstract String convert(T rules);
}
