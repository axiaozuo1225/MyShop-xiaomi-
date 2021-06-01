package com.qf.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
    private String oid;
    private int uid;
    private int aid;
    private BigDecimal money;
    private int state;
    private Date time;
    ;
    private Address address;
    private List<Item> list;

    public Orders() {
    }

    public Orders(String oid, int uid, int aid, BigDecimal money, int state, Date time, Address address, List<Item> list) {
        this.oid = oid;
        this.uid = uid;
        this.aid = aid;
        this.money = money;
        this.state = state;
        this.time = time;
        this.address = address;
        this.list = list;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", uid=" + uid +
                ", aid=" + aid +
                ", money=" + money +
                ", state=" + state +
                ", time=" + time +
                ", address=" + address +
                ", list=" + list +
                '}';
    }
}
