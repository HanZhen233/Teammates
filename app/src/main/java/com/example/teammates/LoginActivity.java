package com.example.teammates;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teammates.okhttp.ExchangeMessage;

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private Button register;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        /*
        获取fragment_me里的 一个textView  更改登录状态,,status
         */
        LayoutInflater inflater=LayoutInflater.from(this);
        final View textEntryView=inflater.inflate(R.layout.fragment_me,null);
     //   final TextView status_in=(TextView)textEntryView.findViewById(R.id.status_in);
       // final TextView status_out=(TextView)textEntryView.findViewById(R.id.status_out);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                // 如果账号是admin且密码是123456，就认为登录成功

                ExchangeMessage.sendrequestWithOkHttp(account,password);//发送信息到后台  并获取个人信息

//                if (account.equals("admin") && password.equals("123456")) {
//                    editor = pref.edit();
//                    if (rememberPass.isChecked()) { // 检查复选框是否被选中
//                        editor.putBoolean("remember_password", true);
//                        editor.putString("account", account);
//                        editor.putString("password", password);
//                    } else {
//                        editor.clear();
//                    }
//                    editor.apply();
//

                  //  status_in.setVisibility(View.VISIBLE);
                    //status_out.setVisibility(View.GONE);

                    changeInfo();//发消息给后台，然后更改本地数据库中的个人信息，即Person表

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(LoginActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void changeInfo(){



    }


}
