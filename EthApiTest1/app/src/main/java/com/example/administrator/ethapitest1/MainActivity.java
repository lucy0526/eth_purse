package com.example.administrator.ethapitest1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.concurrent.Executors;

import rx.Subscription;

import static android.os.Environment.getDataDirectory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LongTimeTask().executeOnExecutor(Executors.newCachedThreadPool());
    }

    private class LongTimeTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            Web3j web3 = Web3jFactory.build(new HttpService("http://192.168.100.113:8545/"));  // 默认 http://localhost:8545/

            Web3ClientVersion web3ClientVersion = null;
            try {
                web3ClientVersion = web3.web3ClientVersion().send();
                String clientVersion = web3ClientVersion.getWeb3ClientVersion();
                Log.d(TAG, "================onCreate: " + clientVersion);
            } catch (IOException e) {
                e.printStackTrace();
            }

            createOrUseWallet(web3);

            return null;
        }
    }

    private void createOrUseWallet(Web3j web3) {
        try {
            String fileName;
            String password = "123";
            File path = new File(String.valueOf(MainActivity.this.getCacheDir()));
            if (!path.exists()) {
                path.mkdir();
            }
            fileName = WalletUtils.generateLightNewWalletFile(password, new File(String.valueOf(path)));
            Log.e("TAG", "generateWallet: " + path + "/" + fileName);

            File file = new File(path+"/"+fileName);
            Credentials credentials = null;
            credentials = WalletUtils.loadCredentials(password, file);

            /**
             * 1. ethClient
             * 2. credentials(keystore)
             * 3. to
             * 4. 交易钱
             * 5. 钱的单位：Convert.Unit.ETHER
             * */
            TransactionReceipt transactionReceipt = Transfer.sendFunds(
                    web3, credentials, "0xf3a0580263b477502f9675886ad5bcac66b2ea66",
                    BigDecimal.valueOf(1.0), Convert.Unit.ETHER)
                    .send();

            String tranceactionHash = transactionReceipt.getTransactionHash();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
