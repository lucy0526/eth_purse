package com.example.administrator.purseui2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();//隐藏 自带的标题栏
        }
    }
}
