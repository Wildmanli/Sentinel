package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.v2.AuthorityRuleEntityV2;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.v2.ParamFlowRuleEntityV2;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author lihao
 * @date 2021-12-24
 */
@Configuration
public class NacosConfig {

    private static final String SERVER_ADDR = "serverAddr";

    private static final String NAMESPACE = "namespace";

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    //====================流控规则 Converter
    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    //====================降级规则 Converter
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }

    //====================热点规则 Converter
    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramsRuleEntityEncoder() {
        return list -> JSON.toJSONString(list
                .stream()
                .map(ParamFlowRuleEntityV2::fromParamFlowRuleEntityV1)
                .collect(Collectors.toList()));
//        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<ParamFlowRuleEntity>> paramsRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntityV2.class)
                .stream()
                .map(ParamFlowRuleEntityV2::toParamFlowRuleEntityV1)
                .collect(Collectors.toList());
//        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }

    //====================系统规则 Converter
    @Bean
    public Converter<List<SystemRuleEntity>, String> systemRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<SystemRuleEntity>> systemRuleEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }

    //====================授权规则 Converter
    @Bean
    public Converter<List<AuthorityRuleEntity>, String> authRuleEntityEncoder() {
        return list -> JSON.toJSONString(list
                .stream()
                .map(AuthorityRuleEntityV2::fromAuthorityRuleEntityV1)
                .collect(Collectors.toList()));
//        return JSON::toJSONString;
    }

    @Bean
    Converter<String, List<AuthorityRuleEntity>> authRuleEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleEntityV2.class)
                .stream()
                .map(AuthorityRuleEntityV2::toAuthorityRuleEntityV1)
                .collect(Collectors.toList());
//        return s -> JSON.parseArray(s, AuthorityRuleEntity.class);
    }

    //====================网关限流规则 Converter
    @Bean
    public Converter<List<GatewayFlowRuleEntity>, String> gatewayFlowRuleEntityEncoder() {
        return JSON::toJSONString;
    }


    @Bean
    Converter<String, List<GatewayFlowRuleEntity>> gatewayFlowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, GatewayFlowRuleEntity.class);
    }

    //====================网关API限流规则 Converter
    @Bean
    public Converter<List<ApiDefinitionEntity>, String> gatewayApiRuleEntityEncoder() {
        return JSON::toJSONString;
    }


    @Bean
    Converter<String, List<ApiDefinitionEntity>> gatewayApiRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ApiDefinitionEntity.class);
    }

    @Bean
    public ConfigService nacosConfigService(NacosProperties nacosProperties) throws Exception {
        Properties properties = new Properties();
        properties.put(SERVER_ADDR, nacosProperties.getServerAddr());
        String namespace = nacosProperties.getNamespace();
        if (StringUtil.isNotBlank(namespace)) {
            properties.put(NAMESPACE, namespace);
        }
        String username = nacosProperties.getUsername();
        if (StringUtil.isNotBlank(username)) {
            properties.put(USERNAME, username);
        }
        String password = nacosProperties.getPassword();
        if (StringUtil.isNotBlank(password)) {
            properties.put(PASSWORD, password);
        }
        return ConfigFactory.createConfigService(properties);
    }
}
