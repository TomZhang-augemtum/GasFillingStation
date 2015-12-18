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
        String sCorpID = "wx75a40ad206394845";
        String sEncodingAESKey = "vMpk78HxIOg9sItzl5QeRhVPYnZfiuAXB8NOlcjBeWC";
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

        String sEchoStr = null;
        System.out.println(msg);
        return null;
    }
}
