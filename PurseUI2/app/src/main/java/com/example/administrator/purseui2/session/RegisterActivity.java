package com.example.administrator.purseui2.session;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.purseui2.BaseActivity;
import com.example.administrator.purseui2.MainActivity;
import com.example.administrator.purseui2.R;
import com.example.administrator.purseui2.entity.User;

import org.litepal.LitePal;

import java.util.UUID;

public class RegisterActivity extends BaseActivity {
    ImageButton backImb;
    TextView createTvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        backImb = (ImageButton) findViewById(R.id.back);
        backImb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText userNameEdt = (EditText) findViewById(R.id.register_userName_edt);
        final EditText passwordEdt = (EditText) findViewById(R.id.register_userPassword_edt);
        final EditText rePasswordEdt = (EditText) findViewById(R.id.register_userPassword_edt);
        final EditText passwordReminderEdt = (EditText) findViewById(R.id.register_remind_password_edt);
        createTvw = (TextView) findViewById(R.id.session_create_user_commit_tvw);

        passwordEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "我们不会帮您存储密码，也无法帮您找回密码，请务必牢记。", Toast.LENGTH_LONG).show();
            }
        });


        createTvw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordEdt.getText() == rePasswordEdt.getText() && passwordEdt.getText().length() > 8) {
                    createUser(userNameEdt.getText().toString(), passwordEdt.getText().toString(), passwordReminderEdt.getText().toString());
                } else if (passwordEdt.getText().length() < 8) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setMessage("密码不能少于8位字符，建议混合大小写字母" +
                            "数字、字符");
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                } else if (passwordEdt.getText() != rePasswordEdt.getText()) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(RegisterActivity.this);
                    alertDialog.setMessage("密码不一致");
                    alertDialog.setCancelable(true);
                    alertDialog.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }

    private void createUser(String userName, String password, String reminderPwd) {
        LitePal.getDatabase();
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setUserPasswordToken(reminderPwd);
        user.setUserId(UUID.randomUUID().toString());
        user.save();

        BaseActivity.myself.setUserId(user.getUserId());
        BaseActivity.userPassword = password;
        Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
