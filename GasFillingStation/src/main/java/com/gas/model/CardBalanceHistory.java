package com.gas.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.gas.utils.DateUtil;

@Entity
public class CardBalanceHistory {
    @Id
    private Date time;
    private String type;
    private double balance;
    private double money;

    public String getTime() {
        return DateUtil.parseToLocal(time, "yyyy-MM-dd hh:mm:ss");
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
