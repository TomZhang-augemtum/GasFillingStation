package com.gas.model;

public class Sale {
    private Long id;
    private String name;
    private String location;
    private double gasTotalNum;
    private double moneyTotalNum;

    public Sale(Long id, double gasTotalNum, double moneyTotalNum) {
        super();
        this.id = id;
        this.gasTotalNum = gasTotalNum;
        this.moneyTotalNum = moneyTotalNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getGasTotalNum() {
        return gasTotalNum;
    }

    public void setGasTotalNum(double gasTotalNum) {
        this.gasTotalNum = gasTotalNum;
    }

    public double getMoneyTotalNum() {
        return moneyTotalNum;
    }

    public void setMoneyTotalNum(double moneyTotalNum) {
        this.moneyTotalNum = moneyTotalNum;
    }

}
