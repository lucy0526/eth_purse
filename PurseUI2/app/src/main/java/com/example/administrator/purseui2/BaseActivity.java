package com.example.administrator.purseui2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.purseui2.entity.Account;
import com.example.administrator.purseui2.entity.User;

import java.util.UUID;

public class BaseActivity extends AppCompatActivity {
    public static String filePath;
    public static Account myself = new Account();
    public static String genPointPath;
    public static String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        filePath = BaseActivity.this.getFilesDir().toString();
        genPointPath = "http://192.168.100.113:8545/";

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();//隐藏 自带的标题栏
        }
    }
}
