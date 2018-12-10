package com.example.administrator.purseui2.entity;

/**
 * Created by Administrator on 2018/11/21.
 */

public class Account {
    private String accountName;
    private int accountImage;
    private String passwordReminder;
    private String memonic;
    private String keystore;
    private String privateKey;
    private String addressHexString;
    private String accountMoney;
    public Account() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(int accountImage) {
        this.accountImage = accountImage;
    }

    public String getPasswordReminder() {
        return passwordReminder;
    }

    public void setPasswordReminder(String passwordReminder) {
        this.passwordReminder = passwordReminder;
    }

    public String getMemonic() {
        return memonic;
    }

    public void setMemonic(String memonic) {
        this.memonic = memonic;
    }

    public String getKeystore() {
        return keystore;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAddressHexString() {
        return addressHexString;
    }

    public void setAddressHexString(String addressHexString) {
        this.addressHexString = addressHexString;
    }

    public String getAccountMoney() {
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public Account(String accountName, int accountImage, String passwordReminder, String memonic, String keystore, String privateKey, String addressHexString, String accountMoney) {
        this.accountName = accountName;
        this.accountImage = accountImage;
        this.passwordReminder = passwordReminder;
        this.memonic = memonic;
        this.keystore = keystore;
        this.privateKey = privateKey;
        this.addressHexString = addressHexString;
        this.accountMoney = accountMoney;
    }
}
