package com.gas.wechat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackController {

    @RequestMapping("/wechat")
    public String list(HttpServletRequest request, String msg_signature, String timestamp, String nonce,
            String echostr) {
        System.out.println(msg_signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        return echostr;
    }
}
