package com.example.teammates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teammates.db.user.User;
import com.example.teammates.okhttp.ExchangeMessage;
import com.example.teammates.okhttp.URL;

import org.litepal.crud.DataSupport;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private EditText university;
    private EditText major;
    private EditText technology;
    private EditText phone;
    private EditText mail;
    private EditText qq;
    private EditText sex;
    private Button send;

    private void initView(){
        name=(EditText)findViewById(R.id.name_content);
        password=(EditText)findViewById(R.id.password_content) ;
        university=(EditText)findViewById(R.id.university_content);
        major=(EditText)findViewById(R.id.major_content);
        technology=(EditText)findViewById(R.id.technology_content);
        phone=(EditText)findViewById(R.id.phone_content);

        qq=(EditText)findViewById(R.id.qq_content);
        sex=(EditText)findViewById(R.id.sex_content);
        mail=(EditText)findViewById(R.id.mail_content);
        send=(Button)findViewById(R.id.send);

    }


    public String json;
    public void savePerson(User user){
        DataSupport.deleteAll(User.class);

        user.setName(name.getText().toString());
        user.setPassword(password.getText().toString());
        user.setSex(sex.getText().toString());
        user.setUniversity(university.getText().toString());
        user.setMajor(major.getText().toString());
        user.setTechnology(technology.getText().toString());
        user.setPhone(phone.getText().toString());
        user.setQq(qq.getText().toString());
        user.setEmail(mail.getText().toString());

        user.save();


    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user =new User();
                savePerson(user);
                ExchangeMessage.postRegister(URL.post_register,user,new okhttp3.Callback(){
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(RegisterActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call,Response response) throws IOException {
                        final String backData=response.body().string();
                        RegisterActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,backData,Toast.LENGTH_SHORT).show();
                                if(backData.equals("添加成功")){

                                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


                    }
                });//将 person_data 发到后台


            }
        });
    }
}
