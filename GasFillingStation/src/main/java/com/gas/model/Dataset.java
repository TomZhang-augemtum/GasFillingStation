package com.gas.model;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private String fillColor;
    private String strokeColor;
    private String pointColor;
    private String pointStrokeColor;
    private List<Double> data = new ArrayList<Double>();

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getPointColor() {
        return pointColor;
    }

    public void setPointColor(String pointColor) {
        this.pointColor = pointColor;
    }

    public String getPointStrokeColor() {
        return pointStrokeColor;
    }

    public void setPointStrokeColor(String pointStrokeColor) {
        this.pointStrokeColor = pointStrokeColor;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
}