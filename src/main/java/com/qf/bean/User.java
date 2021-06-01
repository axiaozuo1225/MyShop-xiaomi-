package com.qf.bean;

import java.io.Serializable;

public class User implements Serializable {
    private int uid;
    private String username;
    private String password;
    private String email;
    private String gender;
    private int state;
    private int role;
    private String code;

    public User() {
    }

    public User(int uid, String username, String password, String email, String gender, int state, int role, String code) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.state = state;
        this.role = role;
        this.code = code;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", state=" + state +
                ", role=" + role +
                ", code='" + code + '\'' +
                '}';
    }
}
