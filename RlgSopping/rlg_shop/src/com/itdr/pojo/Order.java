package com.itdr.pojo;

import java.util.Date;

public class Order {
    private long oid;
    private String pname;
    private double payment;
    private Integer payType=0;
    private String payTypeDesc;
    private Integer status=0;
    private String statusDesc;
    private Date time;

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeDesc() {
        return payTypeDesc;
    }

    public void setPayTypeDesc(String payTypeDesc) {
        this.payTypeDesc = payTypeDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", pname='" + pname + '\'' +
                ", payment=" + payment +
                ", payType=" + payType +
                ", payTypeDesc='" + payTypeDesc + '\'' +
                ", status=" + status +
                ", statusDesc='" + statusDesc + '\'' +
                ", time=" + time +
                '}';
    }
}
