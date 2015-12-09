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

    @RequestMapping("/wechat/test")
    public boolean hehe(HttpServletRequest request, String msg_signature, String timestamp, String nonce,
            String echostr) {
        Boolean flag = new Boolean(true);
        test(flag);
        return flag.booleanValue();
    }

    public void test(Boolean flag) {
        flag = new Boolean(false);
    }
}
