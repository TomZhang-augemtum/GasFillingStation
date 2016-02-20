package com.gas.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Setting implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String settingkey;
    private String value;

    public String getSettingkey() {
        return settingkey;
    }

    public void setSettingkey(String settingkey) {
        this.settingkey = settingkey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
