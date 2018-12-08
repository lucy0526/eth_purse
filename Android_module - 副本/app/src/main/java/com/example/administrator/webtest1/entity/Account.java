package com.example.administrator.webtest1.entity;

/**
 * Created by Administrator on 2018/11/21.
 */

public class Account {
    private String addressHexString;
    private String balance;

    public Account(String addressHexString, String balance) {
        this.addressHexString = addressHexString;
        this.balance = balance;
    }

    public Account() {
    }

    public String getAddressHexString() {
        return addressHexString;
    }

    public void setAddressHexString(String addressHexString) {
        this.addressHexString = addressHexString;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
