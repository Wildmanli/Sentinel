package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.util.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lihao
 * @date 2021-12-24
 */
@Component
@ConfigurationProperties(prefix = "sentinel.nacos")
public class NacosProperties {
    private static final String NACOS_SERVER_ADDR = "NACOS_SERVER_ADDR";
    private static final String NACOS_GROUP_ID = "NACOS_GROUP_ID";
    private static final String NACOS_NAMESPACE = "NACOS_NAMESPACE";
    private static final String NACOS_PORT = "NACOS_PORT";
    private static final String NACOS_IP = "NACOS_IP";
    private static final String NACOS_USERNAME = "NACOS_USERNAME";
    private static final String NACOS_PASSWORD = "NACOS_PASSWORD";

    private String serverAddr;

    private String ip = "localhost";

    private String port = "8848";

    private String namespace;

    private String groupId = NacosConfigUtil.DEFAULT_GROUP_ID;

    private String username;

    private String password;

    private long timeoutMs = 3000L;

    public String getServerAddr() {
        String nacosServerAddr = getEnv(NACOS_SERVER_ADDR);
        if (StringUtil.isNotBlank(nacosServerAddr)) {
            return nacosServerAddr;
        }
        return StringUtil.isNotBlank(this.serverAddr) ? this.serverAddr : this.getIp() + ":" + this.getPort();
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getIp() {
        String nacosIp = getEnv(NACOS_IP);
        if (StringUtil.isNotBlank(nacosIp)) {
            return nacosIp;
        }
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        String nacosPort = getEnv(NACOS_PORT);
        if (StringUtil.isNotBlank(nacosPort)) {
            return nacosPort;
        }
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNamespace() {
        String nacosNamespace = getEnv(NACOS_NAMESPACE);
        if (StringUtil.isNotBlank(nacosNamespace)) {
            return nacosNamespace;
        }
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getGroupId() {
        String nacosGroupId = getEnv(NACOS_GROUP_ID);
        if (StringUtil.isNotBlank(nacosGroupId)) {
            return nacosGroupId;
        }
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        String nacosUsername = getEnv(NACOS_USERNAME);
        if (StringUtil.isNotBlank(nacosUsername)) {
            return nacosUsername;
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        String nacosPassword = getEnv(NACOS_PASSWORD);
        if (StringUtil.isNotBlank(nacosPassword)) {
            return nacosPassword;
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTimeoutMs() {
        return timeoutMs;
    }

    public void setTimeoutMs(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    private static String getEnv(String key) {
        return System.getenv(key);
    }
}
