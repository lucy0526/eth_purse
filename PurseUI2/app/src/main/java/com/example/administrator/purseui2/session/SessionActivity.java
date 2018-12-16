package com.example.administrator.purseui2.session;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.purseui2.BaseActivity;
import com.example.administrator.purseui2.R;

public class SessionActivity extends BaseActivity implements View.OnClickListener {
    TextView enterGenWebTvw;
TextView createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        enterGenWebTvw = (TextView) findViewById(R.id.enter_gen_web_tvw);
        enterGenWebTvw.setOnClickListener(this);

        createUser = (TextView) findViewById(R.id.session_create_user);
        createUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.enter_gen_web_tvw:
                Intent enterGenWebIntent = new Intent(this, GenWebActivity.class);
                startActivity(enterGenWebIntent);
                break;

            case R.id.session_create_user:
                Intent createUserIntent = new Intent(this, RegisterActivity.class);
                startActivity(createUserIntent);
                break;
            default:
                break;
        }
    }
}
