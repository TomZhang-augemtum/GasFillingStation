package com.gas.wechat;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.JssdkConfig;
import com.gas.model.User;
import com.gas.model.WechatConfig;
import com.gas.service.UserService;
import com.gas.utils.HttpClientUtil;
import com.gas.utils.StringUtil;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpInMemoryConfigStorage;

@RestController
public class JSSDKController {
    @Autowired
    private UserService userService;

    @RequestMapping("/api/jssdk/config")
    public JssdkConfig list(HttpServletRequest request, String msg_signature, String url) {
        WxCpInMemoryConfigStorage config = WechatConfig.getWechatConfig().getWxCpInMemoryConfigStorage();
        String ticket = null;
        try {
            ticket = WechatConfig.getWechatConfig().getWxCpService().getJsapiTicket();
        } catch (WxErrorException e1) {
            e1.printStackTrace();
        }
        JssdkConfig jsConfig = new JssdkConfig();
        jsConfig.setAppid(config.getCorpId());
        jsConfig.setNonceStr(StringUtil.getRandomString(16));
        jsConfig.setTimestamp(new Date().getTime() + "");
        jsConfig.setUrl(url);
        jsConfig.setSignature(StringUtil.SHA1("jsapi_ticket=" + ticket + "&noncestr=" + jsConfig.getNonceStr()
                + "&timestamp=" + jsConfig.getTimestamp() + "&url=" + url));
        return jsConfig;
    }

    @RequestMapping("/wechat/oauth")
    public void oauth(HttpServletRequest request,HttpServletResponse response, String code, String state) {
        try {
            String url = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";
            String param = "access_token=" + WechatConfig.getWechatConfig().getWxCpService().getAccessToken() + "&code="
                    + code;
            String result = HttpClientUtil.sendGet(url, param);
            System.err.println(result);
            try{
                JSONObject object = new JSONObject(result);
                String userid = (String) object.get("UserId");
                User user = userService.wxLogin(userid);
                if (user == null) {
                    response.sendRedirect("/error");
                    return;
                }
                request.getSession().setAttribute("user", user);
                Cookie cookie = new Cookie("user", userid);
                cookie.setMaxAge(3600 * 24 * 7);
                response.addCookie(cookie);
                response.sendRedirect(state);
            } catch (JSONException e) {
                System.err.println("用户未注册");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
