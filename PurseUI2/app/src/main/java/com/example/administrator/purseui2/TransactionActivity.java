package com.example.administrator.purseui2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.purseui2.entity.Account;
import com.example.administrator.purseui2.entity.SqlHelper;
import com.example.administrator.purseui2.fragment.OneFragment;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class TransactionActivity extends BaseActivity {
    private static final String TAG = "TransactionActivity";
    private String gasTxt;
    Spinner gasSpn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        TextView transactionCommitTxv = (TextView) findViewById(R.id.commit_transaction);

        gasSpn = (Spinner) findViewById(R.id.gas_spr);
        gasSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                gasTxt = gasSpn.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        transactionCommitTxv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TransactionLongTimeTask().executeOnExecutor(Executors.newCachedThreadPool());
            }
        });
    }

    private class TransactionLongTimeTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            try {
                super.onPostExecute(o);

                Web3j web3 = Web3jFactory.build(new HttpService(BaseActivity.genPointPath));
                String from = BaseActivity.myself.getAddress();
                String to = ((EditText) findViewById(R.id.transaction_to_edt)).getText().toString();
                String valueText = ((EditText) findViewById(R.id.transaction_money_edt)).getText().toString();
                if (valueText == null || valueText.trim().isEmpty()) {
                    Toast.makeText(TransactionActivity.this, "交易地址有误！", Toast.LENGTH_SHORT).show();
                    return;
                }
                BigInteger value = BigInteger.valueOf(Integer.parseInt(valueText));

                String password = SqlHelper.findUserByUserId(BaseActivity.myself.getUserId()).getUserPassword();
                Credentials credentials = WalletUtils.loadCredentials(password, BaseActivity.myself.getKeystoreName());
                BigInteger gasPrice = BigInteger.valueOf(Integer.parseInt(gasTxt));
                BigInteger gasLimit = BigInteger.valueOf(21000);
                String data = "";
                data = ((EditText) findViewById(R.id.transaction_remark_edt)).getText().toString();
                EthSendTransaction transaction = transfer(web3, from, to, value, credentials, gasPrice, gasLimit, data);
                String log = transaction.getTransactionHash();
                String response = transaction.getRawResponse();
                Response.Error err = transaction.getError();
                String mess = err.getMessage();
                String re = transaction.getResult();


                Log.d("===", "onPostExecute: "+mess);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 发起一笔交易（自定义参数）
         *
         * @param from     发起人钱包地址
         * @param to       转入的钱包地址
         * @param value    转账金额，单位是wei
         * @param gasPrice 转账费用
         * @param gasLimit
         * @param data     备注的信息
         */
        public EthSendTransaction transfer(Web3j web3,
                                           String from,
                                           String to,
                                           BigInteger value,
                                           Credentials credentials,
                                           BigInteger gasPrice,
                                           BigInteger gasLimit,
                                           String data) throws Exception {
            //获取nonce，交易笔数
            BigInteger nonce = getNonce(web3, from);
            //创建RawTransaction交易对象
            RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, to, value);
            //签名Transaction，这里要对交易做签名
            byte[] signMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signMessage);
            //发送交易
            EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();

            return ethSendTransaction;
        }

        /**
         * 获取nonce，交易笔数
         *
         * @param from
         * @return
         * @throws ExecutionException
         * @throws InterruptedException
         */
        private BigInteger getNonce(Web3j web3, String from) throws ExecutionException, InterruptedException {
            EthGetTransactionCount transactionCount = web3.ethGetTransactionCount(from, DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger nonce = transactionCount.getTransactionCount();
            Log.i(TAG, "transfer nonce : " + nonce);
            return nonce;
        }
    }
}
