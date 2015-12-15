package com.gas.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.gas.utils.StringUtil;

import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.api.WxCpServiceImpl;

public class WechatConfig {

    private static WechatConfig wechatConfig = null;
    private static WxCpInMemoryConfigStorage wxCpInMemoryConfigStorage;
    private static WxCpServiceImpl wxCpService;
    private static String jsapi_ticket;
    private static Long lastTime = 0L;

    public static WechatConfig getWechatConfig() {
        if (wechatConfig == null) {
            wechatConfig = new WechatConfig();
        }
        return wechatConfig;
    }

    public static WxCpInMemoryConfigStorage getWxCpInMemoryConfigStorage() {
        return wxCpInMemoryConfigStorage;
    }

    public static void setWxCpInMemoryConfigStorage(WxCpInMemoryConfigStorage wxCpInMemoryConfigStorage) {
        WechatConfig.wxCpInMemoryConfigStorage = wxCpInMemoryConfigStorage;
    }

    public static WxCpServiceImpl getWxCpService() {
        return wxCpService;
    }

    public static void setWxCpService(WxCpServiceImpl wxCpService) {
        WechatConfig.wxCpService = wxCpService;
    }

    public static String getJsapi_ticket() {
        try {
            if (lastTime == 0L) {
                Date date = new Date();
                lastTime = date.getTime() / 1000;
                getJsapiTicket();
            } else {
                Date date = new Date();
                if (date.getTime() / 1000 - lastTime > 7200) {
                    lastTime = date.getTime() / 1000;
                    getJsapiTicket();
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsapi_ticket;
    }

    private static void getJsapiTicket() throws ClientProtocolException, IOException {
        // 创建HttpClient实例
        HttpClient httpclient = new DefaultHttpClient();
        System.out.println(wxCpInMemoryConfigStorage.getCorpId());
        System.out.println(wxCpInMemoryConfigStorage.getCorpSecret());
        System.out.println(wxCpInMemoryConfigStorage.getAccessToken());
        // 创建Get方法实例
        String url = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="
                + wxCpInMemoryConfigStorage.getAccessToken();
        HttpGet httpgets = new HttpGet(url);
        HttpResponse response = httpclient.execute(httpgets);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instreams = entity.getContent();
            String str = StringUtil.convertStreamToString(instreams);
            jsapi_ticket = StringUtil.toMap(str).get("ticket");
        }
    }
}
