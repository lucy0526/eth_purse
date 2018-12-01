package com.example.administrator.webtest1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddAccountActivity extends AppCompatActivity  {
    EditText passwordEtt;
    EditText rePasswordEtt;
    Button cancelBtn;
    Button submitBtn;
    String address = "";
    private static final String TAG = "AddAccountActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        cancelBtn = (Button) findViewById(R.id.cancel_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        submitBtn = (Button) findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordEtt = (EditText) findViewById(R.id.password_ett);
                rePasswordEtt = (EditText) findViewById(R.id.rePassword_ett);
                if (passwordEtt.getText().toString().equals(rePasswordEtt.getText().toString())){
                    String password = passwordEtt.getText().toString();
                    new LongTimeTask().executeOnExecutor(Executors.newCachedThreadPool(), password);
                }else{
                    Toast.makeText(AddAccountActivity.this, "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    passwordEtt.setText("");
                    rePasswordEtt.setText("");
                }
            }
        });
    }

    private class LongTimeTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            String addAccount_url = MainActivity.hostUrl+"/addAccount";
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("password", params[0].toString())
                    .build();
            Request request = new Request.Builder()
                    .url(addAccount_url)
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    address = response.body().string();
                    Log.d(TAG, "doInBackground: "+address);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return params[0];
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddAccountActivity.this);
            dialog.setTitle("新账号的地址为：");
            dialog.setMessage(address);
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(AddAccountActivity.this, AccountActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }
    }
}
