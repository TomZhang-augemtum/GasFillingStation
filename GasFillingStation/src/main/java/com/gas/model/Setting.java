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
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
