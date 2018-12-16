package com.example.administrator.purseui2.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/12/15.
 */

public class Account extends DataSupport {
    private String userId;
    private String address;
    private String money;
    private String keystoreName;

    public Account() {
    }

    public Account(String userId, String address, String money, String keystoreName) {
        this.userId = userId;
        this.address = address;
        this.money = money;
        this.keystoreName = keystoreName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getKeystoreName() {
        return keystoreName;
    }

    public void setKeystoreName(String keystoreName) {
        this.keystoreName = keystoreName;
    }
}
