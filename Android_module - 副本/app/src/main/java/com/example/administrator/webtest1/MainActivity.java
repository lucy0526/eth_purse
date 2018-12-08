package com.example.administrator.webtest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final public static String hostUrl = "http://192.168.100.113:8080";
//    TextView respTvw;
    Button accountBtn;
    Button transactionBtn;
    Button historyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountBtn = (Button) findViewById(R.id.account_btn);
        accountBtn.setOnClickListener(this);

        transactionBtn = (Button) findViewById(R.id.transaction_btn);
        transactionBtn.setOnClickListener(this);

        historyBtn = (Button) findViewById(R.id.history_btn);
        historyBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.account_btn:
                Intent accountIntent = new Intent(this, AccountActivity.class);
                startActivity(accountIntent);
                break;

            case R.id.transaction_btn:
                Intent transactionIntent = new Intent(this, TransactionActivity.class);
                startActivity(transactionIntent);
                break;

            case R.id.history_btn:
                Intent historyIntent = new Intent(this, HistoryActivity.class);
                startActivity(historyIntent);
                break;

            default:
                break;
        }
    }
}
