package com.example.teammates;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teammates.DataActivities.DataSelfActivity;
import com.example.teammates.db.user.User;
import com.example.teammates.okhttp.ExchangeMessage;
import com.example.teammates.okhttp.URL;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Response;

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

                ExchangeMessage.postLogin(account,getBaseContext(),password,new okhttp3.Callback(){

                    public void onFailure(Call call, IOException e) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();

                            }
                        });

                    }

                    @Override
                    public void onResponse(Call call,Response response) throws IOException {
                        final String backData=response.body().string();
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Log.d("backdata", backData);
                            }
                        });
                    }
                });

                ExchangeMessage.getLogin(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        final String backdata=response.body().string();
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Gson gson=new Gson();
                                User user=gson.fromJson(backdata,User.class);
                                Log.d("fuck","fuck"+backdata);
                              if(user!=null){
                                  DataSupport.deleteAll(User.class);
                                  user.save();

                                  Toast.makeText(LoginActivity.this,"登录成功"+backdata,Toast.LENGTH_SHORT).show();

                                  Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                                  startActivity(intent);
                                  finish();
                              }else{
                                  Toast.makeText(LoginActivity.this,"因为不知名原因失败，可能是服务器关了",Toast.LENGTH_SHORT).show();

                              }



                            }
                        });


                    }

                });

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

}
