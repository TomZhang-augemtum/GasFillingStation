package com.gas.wechat;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.JssdkConfig;
import com.gas.model.WechatConfig;
import com.gas.utils.StringUtil;

import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;

@RestController
public class JSSDKController {

    @RequestMapping("/api/jssdk/config")
    public JssdkConfig list(HttpServletRequest request, String msg_signature, String url) {
        WxCpInMemoryConfigStorage config = WechatConfig.getWxCpInMemoryConfigStorage();
        String ticket = WechatConfig.getJsapi_ticket();
        JssdkConfig jsConfig = new JssdkConfig();
        jsConfig.setAppid(config.getCorpId());
        jsConfig.setNonceStr(StringUtil.getRandomString(16));
        jsConfig.setTimestamp(new Date().getTime() + "");
        jsConfig.setUrl(url);
        System.out.println("jsapi_ticket=" + ticket + "timestamp=" + jsConfig.getTimestamp() + ", nonceStr="
                + jsConfig.getNonceStr() + ", url=" + url);
        try {
            jsConfig.setSignature(SHA1.gen("jsapi_ticket=" + ticket + "timestamp=" + jsConfig.getTimestamp()
                    + ", nonceStr=" + jsConfig.getNonceStr() + ", url=" + url));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return jsConfig;
    }

}
