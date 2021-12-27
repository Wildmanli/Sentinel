package com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.v2;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.slots.block.Rule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;

import java.util.Date;

/**
 * @author lihao
 * @date 2021-12-27 16:35
 */
public class ParamFlowRuleEntityV2 extends ParamFlowRule implements RuleEntity {

    private Long id;
    private String app;
    private String ip;
    private Integer port;
    private Date gmtCreate;
    private Date gmtModified;

    public ParamFlowRuleEntityV2() {
    }

    public ParamFlowRuleEntityV2(ParamFlowRule rule) {
        this.setGrade(rule.getGrade())
                .setParamIdx(rule.getParamIdx())
                .setCount(rule.getCount())
                .setControlBehavior(rule.getControlBehavior())
                .setMaxQueueingTimeMs(rule.getMaxQueueingTimeMs())
                .setBurstCount(rule.getBurstCount())
                .setDurationInSec(rule.getDurationInSec())
                .setParamFlowItemList(rule.getParamFlowItemList())
                .setClusterMode(rule.isClusterMode())
                .setClusterConfig(rule.getClusterConfig())
                .setResource(rule.getResource())
                .setLimitApp(rule.getLimitApp());
    }

    public static ParamFlowRuleEntityV2 fromParamFlowRule(String app, String ip, Integer port, ParamFlowRule rule) {
        return new ParamFlowRuleEntityV2(rule)
                .setApp(app)
                .setIp(ip)
                .setPort(port);
    }

    public static ParamFlowRuleEntityV2 fromParamFlowRuleEntityV1(ParamFlowRuleEntity entity) {
        return fromParamFlowRule(entity.getApp(), entity.getIp(), entity.getPort(), entity.getRule());
    }

    public ParamFlowRuleEntity toParamFlowRuleEntityV1() {
        return ParamFlowRuleEntity.fromParamFlowRule(this.getApp(), this.ip, this.port, this);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getApp() {
        return this.app;
    }

    public ParamFlowRuleEntityV2 setApp(String app) {
        this.app = app;
        return this;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    public ParamFlowRuleEntityV2 setIp(String ip) {
        this.ip = ip;
        return this;
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

    public ParamFlowRuleEntityV2 setPort(Integer port) {
        this.port = port;
        return this;
    }

    @Override
    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public ParamFlowRuleEntityV2 setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public ParamFlowRuleEntityV2 setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public Rule toRule() {
        return this;
    }
}
