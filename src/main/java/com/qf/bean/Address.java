package com.qf.bean;

import java.io.Serializable;

public class Address implements Serializable {
    private int aid;
    private int uid;
    private String detail;
    private String name;
    private String phone;
    private int level;//0

    public Address() {
    }

    public Address(int aid, int uid, String detail, String name, String phone, int level) {
        this.aid = aid;
        this.uid = uid;
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.level = level;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }
}
