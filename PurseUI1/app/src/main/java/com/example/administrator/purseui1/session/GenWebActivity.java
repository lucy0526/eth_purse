package com.example.administrator.purseui1.session;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.purseui1.BaseActivity;
import com.example.administrator.purseui1.R;

public class GenWebActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_web);

        setCustomActionBar();

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
}
