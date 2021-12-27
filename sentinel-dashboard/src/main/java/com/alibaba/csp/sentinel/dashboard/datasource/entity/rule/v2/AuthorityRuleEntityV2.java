package com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.v2;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.slots.block.Rule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;

import java.util.Date;

/**
 * @author lihao
 * @date 2021-12-27 15:45
 */
public class AuthorityRuleEntityV2 extends AuthorityRule implements RuleEntity {

    private Long id;
    private String app;
    private String ip;
    private Integer port;
    private Date gmtCreate;
    private Date gmtModified;

    public AuthorityRuleEntityV2() {
    }

    public AuthorityRuleEntityV2(AuthorityRule rule) {
        this.setStrategy(rule.getStrategy())
                .setResource(rule.getResource())
                .setLimitApp(rule.getLimitApp());
    }

    public static AuthorityRuleEntityV2 fromAuthorityRule(String app, String ip, Integer port, AuthorityRule rule) {
        return new AuthorityRuleEntityV2(rule)
                .setApp(app)
                .setIp(ip)
                .setPort(port);
    }

    public static AuthorityRuleEntityV2 fromAuthorityRuleEntityV1(AuthorityRuleEntity entity) {
        return fromAuthorityRule(entity.getApp(), entity.getIp(), entity.getPort(), entity.getRule());
    }

    public AuthorityRuleEntity toAuthorityRuleEntityV1() {
        return AuthorityRuleEntity.fromAuthorityRule(this.getApp(), this.ip, this.port, this);
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

    public AuthorityRuleEntityV2 setApp(String app) {
        this.app = app;
        return this;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    public AuthorityRuleEntityV2 setIp(String ip) {
        this.ip = ip;
        return this;
    }

    @Override
    public Integer getPort() {
        return this.port;
    }

    public AuthorityRuleEntityV2 setPort(Integer port) {
        this.port = port;
        return this;
    }

    @Override
    public Date getGmtCreate() {
        return this.gmtCreate;
    }

    public AuthorityRuleEntityV2 setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
        return this;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public AuthorityRuleEntityV2 setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
        return this;
    }

    @Override
    public Rule toRule() {
        return this;
    }
}
