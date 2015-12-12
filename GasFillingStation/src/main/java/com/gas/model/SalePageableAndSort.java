package com.gas.model;

import java.util.Date;

public class SalePageableAndSort {
    private String orderColumn;
    private String order;
    private Date fromDate;
    private Date toDate;
    private int page;
    private int size;

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SalePageableAndSort [orderColumn=" + orderColumn + ", order=" + order + ", fromDate=" + fromDate
                + ", toDate=" + toDate + ", page=" + page + ", size=" + size + "]";
    }

}
