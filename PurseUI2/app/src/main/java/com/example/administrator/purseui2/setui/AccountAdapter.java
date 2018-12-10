package com.example.administrator.purseui2.setui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.purseui2.R;
import com.example.administrator.purseui2.entity.Account;

import java.util.List;

/**
 * Created by Administrator on 2018/12/6.
 */

public class AccountAdapter extends ArrayAdapter<Account>{
    private int resourceId;

    public AccountAdapter(Context context, int resource, List<Account> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Account account = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        ImageView accountImageImv = (ImageView) view.findViewById(R.id.account_image_imv);
        TextView accountNameTvw = (TextView) view.findViewById(R.id.account_name_tvw);
        TextView addressHexStringTvw = (TextView) view.findViewById(R.id.address_hexString_tvw);
        TextView accountMoneyTvw = (TextView) view.findViewById(R.id.address_hexString_tvw);

        accountImageImv.setImageResource(account.getAccountImage());
        accountNameTvw.setText(account.getAccountName());
        addressHexStringTvw.setText(account.getAddressHexString());
        accountMoneyTvw.setText("￥ "+account.getAccountMoney());

        return view;
    }
}
