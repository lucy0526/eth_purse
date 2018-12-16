package com.example.administrator.purseui2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.purseui2.entity.Account;

public class BaseActivity extends AppCompatActivity {
    public static String filePath;
    public static Account myself;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        filePath = BaseActivity.this.getFilesDir().toString();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();//隐藏 自带的标题栏
        }
    }
}
