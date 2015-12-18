package com.gas.model;

import java.util.HashMap;
import java.util.Map;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

public class WechatConfig {

    private static WechatConfig wechatConfig = null;
    private WxCpInMemoryConfigStorage wxCpInMemoryConfigStorage;
    private WxCpServiceImpl wxCpService;
    private String domain;
    private Map<String, String> settings = new HashMap<>();
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private WechatConfig() {
        super();
    }

    public static synchronized WechatConfig getWechatConfig() {
        if (wechatConfig == null) {
            wechatConfig = new WechatConfig();
        }
        return wechatConfig;
    }

    public WxCpInMemoryConfigStorage getWxCpInMemoryConfigStorage() {
        return wxCpInMemoryConfigStorage;
    }

    public void setWxCpInMemoryConfigStorage(WxCpInMemoryConfigStorage wxCpInMemoryConfigStorage) {
        this.wxCpInMemoryConfigStorage = wxCpInMemoryConfigStorage;
    }

    public WxCpServiceImpl getWxCpService() {
        return wxCpService;
    }

    public void setWxCpService(WxCpServiceImpl wxCpService) {
        this.wxCpService = wxCpService;
    }

    public Map<String, String> getSettings() {
        return settings;
    }

    public void setSettings(Map<String, String> settings) {
        this.settings = settings;
    }
}
