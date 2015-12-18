package com.gas.config;

import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.gas.model.WechatConfig;
import com.gas.service.SettingService;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        SettingService settingService = event.getApplicationContext().getBean(SettingService.class);
        Map<String, String> settings = settingService.findAll();
        WxCpInMemoryConfigStorage config = new WxCpInMemoryConfigStorage();
        config.setCorpId(settings.get("corpid"));
        config.setCorpSecret(settings.get("secret"));
        config.setAgentId(settings.get("agentid"));
        config.setToken(settings.get("Token"));
        config.setAesKey(settings.get("EncodingAESKey"));
        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);
        WechatConfig.getWechatConfig().setWxCpInMemoryConfigStorage(config);
        WechatConfig.getWechatConfig().setWxCpService(wxCpService);
        WechatConfig.getWechatConfig().setDomain(settings.get("domain"));
        WechatConfig.getWechatConfig().setSettings(settings);
    }
}