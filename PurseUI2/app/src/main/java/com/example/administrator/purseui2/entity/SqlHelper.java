package com.example.administrator.purseui2.entity;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/12/16.
 */

public class SqlHelper {
    public static User findUserByUserId(String userId){
        List<User> users = DataSupport.where("userId=?", userId).find(User.class);
        return users.get(0);
    }
}
