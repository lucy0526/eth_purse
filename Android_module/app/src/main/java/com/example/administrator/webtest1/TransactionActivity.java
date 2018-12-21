package com.example.administrator.webtest1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.webtest1.entity.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TransactionActivity extends AppCompatActivity {
    private static final String TAG = "TransactionActivity";

    String accountList_url = MainActivity.hostUrl+"/accountList";
    String submitTransaction_url = MainActivity.hostUrl+"/submitTransaction";
    String transactionAddress = "";

    List<Account> accountList = new ArrayList<>();
    Spinner accountListSpr;
    Button transactionSubmitBtn;
    Spinner receiverEdt;
    EditText transactionMoneyEdt;
    EditText passwordEdt;
    Spinner gasSpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        accountListSpr = (Spinner) findViewById(R.id.accountList_spr);

        new LongTimeTask().executeOnExecutor(Executors.newCachedThreadPool());

        receiverEdt = (Spinner) findViewById(R.id.receiver_ett);
        transactionMoneyEdt = (EditText) findViewById(R.id.transactionEth_ett);
        gasSpr = (Spinner) findViewById(R.id.gas_spr);
        passwordEdt = (EditText) findViewById(R.id.password_edt);

        transactionSubmitBtn = (Button) findViewById(R.id.transaction_submit_btn);
        transactionSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = (String) accountListSpr.getSelectedItem();
                String to = (String) receiverEdt.getSelectedItem();
                String value = transactionMoneyEdt.getText().toString();
                String gas = (String) gasSpr.getSelectedItem();
                String password = passwordEdt.getText().toString();

                if (password.isEmpty()) {
                    password = null;
                }


            }
        });



    }

    private class SubmitLongTimeTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("from", params[0].toString())
                    .add("to", params[1].toString())
                    .add("value", params[2].toString())
                    .add("gas", params[3].toString())
                    .build();
            Request request  = new Request.Builder()
                    .url(submitTransaction_url)
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
//                    Log.d(TAG, "onClick: "+response.body().string());
                    transactionAddress = response.body().string();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            AlertDialog.Builder dialog = new AlertDialog.Builder(TransactionActivity.this);
            dialog.setTitle("交易成功！");
            dialog.setMessage("交易的地址为："+transactionAddress);
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            dialog.show();
        }
}

    private class LongTimeTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(accountList_url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String resData = response.body().string();
                    Gson gson = new Gson();
                    accountList = gson.fromJson(resData, new TypeToken<List<Account>>(){}.getType());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<String> addressList = new ArrayList<>();
            for (Account account:accountList){
                addressList.add(account.getAddressHexString());
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(TransactionActivity.this, android.R.layout.simple_list_item_1, addressList);
            accountListSpr.setAdapter(arrayAdapter);
            receiverEdt.setAdapter(arrayAdapter);
        }
    }
}
