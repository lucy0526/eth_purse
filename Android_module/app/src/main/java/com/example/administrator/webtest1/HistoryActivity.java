package com.example.administrator.webtest1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.administrator.webtest1.entity.Account;
import com.example.administrator.webtest1.entity.History;
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

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = "HistoryActivity";

    String accountList_url = MainActivity.hostUrl+"/accountList";
    String historyList_url = MainActivity.hostUrl+"/historyList";
    Spinner accountListSpr;
    ArrayAdapter<String> AccountAdapter;
    List<String> accountList;
    List<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        accountListSpr = (Spinner) findViewById(R.id.accountList_spr);
        accountList = new ArrayList<>();

        new LongTimeTask().executeOnExecutor(Executors.newCachedThreadPool());

        accountListSpr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String account = accountListSpr.getSelectedItem().toString();
                new FindHistoryLongTimeTask().executeOnExecutor(Executors.newCachedThreadPool(), new Object[]{account});
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private class FindHistoryLongTimeTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] params) {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("account", params[0].toString())
                    .build();
            Request request = new Request.Builder()
                    .url(historyList_url)
                    .post(requestBody)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String resData = response.body().string();
                    historyList = new Gson().fromJson(resData, new TypeToken<List<History>>(){}.getType());
                    for (History history:historyList){
                        Log.d(TAG, "+++++++++++history: "+history.getFrom());

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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
                    List<Account> accounts = new Gson().fromJson(resData, new TypeToken<List<Account>>(){}.getType());
                    for (Account account:accounts){
                        accountList.add(account.getAddressHexString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            AccountAdapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, accountList);
            accountListSpr.setAdapter(AccountAdapter);
        }
    }
}