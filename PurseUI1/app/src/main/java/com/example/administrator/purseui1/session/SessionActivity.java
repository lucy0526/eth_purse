package com.example.administrator.purseui1.session;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.purseui1.BaseActivity;
import com.example.administrator.purseui1.R;

public class SessionActivity extends BaseActivity implements View.OnClickListener {
    TextView enterGenWebTvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        enterGenWebTvw = (TextView) findViewById(R.id.enter_gen_web_tvw);
        enterGenWebTvw.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enter_gen_web_tvw:
                Intent enterGenWebIntent = new Intent(this, GenWebActivity.class);
                startActivity(enterGenWebIntent);
        }
    }
}
