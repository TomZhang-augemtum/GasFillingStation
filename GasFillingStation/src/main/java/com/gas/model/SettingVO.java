package com.gas.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SettingVO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String EncodingAESKey;
    private String Token;
    private String agentid;
    private String corpid;
    private String domain;
    private String secret;

    public String getEncodingAESKey() {
        return EncodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        EncodingAESKey = encodingAESKey;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Map<String, String> toMap() {
        Map<String, String> res = new HashMap<>();
        res.put("EncodingAESKey", EncodingAESKey);
        res.put("Token", Token);
        res.put("agentid", agentid);
        res.put("corpid", corpid);
        res.put("domain", domain);
        res.put("secret", secret);
        return res;
    }
}
