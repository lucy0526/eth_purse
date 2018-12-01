package com.example.administrator.webtest1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.webtest1.entity.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "AccountActivity";
    ListView accountListLvw;
    List<Account> accountList = new ArrayList<>();
    AccountAdapter accountAdapter;
    Button addAccountBtn;
    Button backMainBtn;

    String accountList_url = MainActivity.hostUrl+"/accountList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        accountListLvw = (ListView) findViewById(R.id.accountList_lvw);

        new LongTimeTask().executeOnExecutor(Executors.newCachedThreadPool());

        addAccountBtn = (Button) findViewById(R.id.addAccount_btn);
        addAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAccountIntent = new Intent(AccountActivity.this, AddAccountActivity.class);
                startActivity(addAccountIntent);
            }
        });

        backMainBtn = (Button) findViewById(R.id.backMain_btn);
        backMainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backMainIntent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(backMainIntent);
            }
        });
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
                if (response.isSuccessful()){
                    String resData = response.body().string();
                    parseJSONWithGson(resData);
//                    publishProgress();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.d(TAG, "onPostExecute: ");
            accountAdapter = new AccountAdapter(AccountActivity.this, R.layout.account_item, accountList);
            accountListLvw.setAdapter(accountAdapter);
        }

        /*@Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            accountAdapter = new AccountAdapter(AccountActivity.this, R.layout.account_item, accountList);
            accountListLvw.setAdapter(accountAdapter);
        }*/

        @Override
        protected void onPreExecute() {
        }

    }

    class AccountAdapter extends ArrayAdapter<Account>{
        private int resourceId;

        public AccountAdapter( Context context, int resource,  List<Account> objects) {
            super(context, resource, objects);
            this.resourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Account account = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            TextView addressHexStringTvw = view.findViewById(R.id.addressHexString_tvw);
            TextView balanceTvw = view.findViewById(R.id.balance_tvw);
            addressHexStringTvw.setText(account.getAddressHexString());
            balanceTvw.setText(account.getBalance());
            return view;
        }

        @Override
        public int getCount() {
            return accountList.size();
        }
    }

    private void parseJSONWithGson(String jsonData)
    {
        Gson gson = new Gson();
        accountList = gson.fromJson(jsonData, new TypeToken<List<Account>>(){}.getType());
    }
}
