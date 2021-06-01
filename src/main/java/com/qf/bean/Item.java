package com.qf.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {
    private int iid;
    private int pid;
    private String oid;
    private int num;
    private BigDecimal money;

    private Product product;

    public Item() {
    }

    public Item(int iid, int pid, String oid, int num, BigDecimal money, Product product) {
        this.iid = iid;
        this.pid = pid;
        this.oid = oid;
        this.num = num;
        this.money = money;
        this.product = product;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", pid=" + pid +
                ", oid='" + oid + '\'' +
                ", num=" + num +
                ", money=" + money +
                ", product=" + product +
                '}';
    }
}
