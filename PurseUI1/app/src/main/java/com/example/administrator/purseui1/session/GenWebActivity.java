package com.example.administrator.purseui1.session;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.purseui1.BaseActivity;
import com.example.administrator.purseui1.R;
import com.example.administrator.purseui1.setui.CommonPopupWindow;

public class GenWebActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_web);

        setCustomActionBar();

        View genWebActionbarView = LayoutInflater.from(this).inflate(R.layout.gen_web_actionbar, null);
        ImageButton moreImb = (ImageButton) genWebActionbarView.findViewById(R.id.gen_web_more_imb);
        moreImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPopupWindow();
            }
        });

        WebView webView = (WebView) findViewById(R.id.gen_web_wbv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");


    }

    private void setCustomActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.show();

        //actionBar的设置(使用自定义的设置)
        actionBar.setCustomView(R.layout.gen_web_actionbar);
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    private void createPopupWindow(){
        CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
                //设置PopupWindow布局
                .setView(R.layout.gen_web_popup_down)
                //设置宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                //设置动画
                .setAnimationStyle(R.style.AnimDown)
                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setBackGroundLevel(0.5f)
                //设置PopupWindow里的子View及点击事件
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        Toast.makeText(GenWebActivity.this, "===========", Toast.LENGTH_SHORT).show();
                    }
                })
                //设置外部是否可点击 默认是true
                .setOutsideTouchable(true)
                //开始构建
                .create();
//弹出PopupWindow
        popupWindow.showAsDropDown(getWindow().getDecorView());
    }
}
