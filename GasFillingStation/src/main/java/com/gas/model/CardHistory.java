package com.gas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class CardHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    private int cardid;
    private int operatorid;
    private Date time;
    private String remark;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getCardid() {
        return cardid;
    }

    public void setCardid(int cardid) {
        this.cardid = cardid;
    }

    public int getOperatorid() {
        return operatorid;
    }

    public void setOperatorid(int operatorid) {
        this.operatorid = operatorid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
