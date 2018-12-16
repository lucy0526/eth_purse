package com.example.administrator.purseui2.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.purseui2.BaseActivity;
import com.example.administrator.purseui2.MainActivity;
import com.example.administrator.purseui2.R;
import com.example.administrator.purseui2.entity.Account;

import org.litepal.LitePal;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

public class OneFragment extends Fragment {
    View view;
    public OneFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);

        createAccount();

        ImageButton addAccountImb = (ImageButton) view.findViewById(R.id.transaction_imb);
        addAccountImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
    private void createAccount() {
        new CreateAccountLongTimeTask().executeOnExecutor(Executors.newCachedThreadPool());
    }
    private class CreateAccountLongTimeTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            try {
                String fileName;
                String password = "123";
                try {
                    File path = new File(String.valueOf(BaseActivity.filePath));
                    if (!path.exists()) {
                        path.mkdir();
                    }
                    fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path)));
                    Log.e("+++", "generateWallet: " + path + "/" + fileName);
                    Credentials credentials = WalletUtils.loadCredentials(password, path + "/" + fileName);
                    String address = credentials.getAddress();
                    String keystoreName = path + "/" + fileName;

                    BaseActivity.myself.setAddress(address);
                    BaseActivity.myself.setMoney("0");
                    BaseActivity.myself.setKeystoreName(keystoreName);
                    BaseActivity.myself.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            TextView accountNameTxv = (TextView) view.findViewById(R.id.account_name_tvw);
            accountNameTxv.setText(BaseActivity.myself.getAddress());
        }
    }
}
