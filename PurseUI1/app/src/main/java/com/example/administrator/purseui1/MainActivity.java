package com.example.administrator.purseui1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.purseui1.fragment.OneFragment;
import com.example.administrator.purseui1.fragment.TwoFragment;
import com.hjm.bottomtabbar.BottomTabBar;


public class MainActivity extends BaseActivity {
    private BottomTabBar mBottomTabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);

        mBottomTabBar
                .init(getSupportFragmentManager())//初始化方法，必须第一个调用；传入参数为V4包下的FragmentManager
                .setImgSize(100,100)//设置ICON图片的尺寸
                .setFontSize(8)//设置文字的尺寸
                .setTabPadding(4,6,10)//设置ICON图片与上部分割线的间隔、图片与文字的间隔、文字与底部的间隔
                .addTabItem("钱包", R.mipmap.purse, OneFragment.class)//设置文字、一张图片、fragment
                .addTabItem("我", R.mipmap.me, TwoFragment.class)//设置文字、两张图片、fragment
                .isShowDivider(false)//设置是否显示分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {

                    }//添加选项卡切换监听
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
                    }
                })
                .setCurrentTab(0);//设置当前选中的Tab，从0开始
    }
}

