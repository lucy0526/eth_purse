package com.example.administrator.purseui2.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/12/14.
 */

public class User extends DataSupport{
    private String UserId;
    private String userName;
    private String userPassword;
    private String userImage;
    private String userEmail;
    private String userPasswordToken;

    public User() {
    }

    public User(String userId, String userName, String userPassword, String userImage, String userEmail, String userPasswordToken) {
        UserId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.userEmail = userEmail;
        this.userPasswordToken = userPasswordToken;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPasswordToken() {
        return userPasswordToken;
    }

    public void setUserPasswordToken(String userPasswordToken) {
        this.userPasswordToken = userPasswordToken;
    }
}
