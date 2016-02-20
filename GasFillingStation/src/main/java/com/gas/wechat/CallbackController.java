package com.gas.wechat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gas.wechat.mp.aes.AesException;
import com.gas.wechat.mp.aes.WXBizMsgCrypt;

@RestController
public class CallbackController {

    @RequestMapping("/wechat")
    public String list(HttpServletRequest request, String msg)
            throws AesException {

        String sToken = "LjLEnZTyM24YWQVcMM5peVxaG0p2J9d";
        String sCorpID = "wxa5cf25672e5e7688";
        String sEncodingAESKey = "vMpk78HxIOg9sItzl5QeRhVPYnZfiuAXB8NOlcjBeWC";
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

        String sEchoStr = null;
        System.out.println(msg);
        return null;
    }

    @RequestMapping("/wechat/verifyurl")
    public String verify(String msg_signature, String timestamp, String nonce, String echostr) throws AesException {

        String sToken = "LjLEnZTyM24YWQVcMM5peVxaG0p2J9d";
        String sCorpID = "wxa5cf25672e5e7688";
        String sEncodingAESKey = "vMpk78HxIOg9sItzl5QeRhVPYnZfiuAXB8NOlcjBeWC";
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
        String sEchoStr = null;
        sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        return sEchoStr;
    }
}
