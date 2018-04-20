package com.example.teammates.DataActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teammates.db.user.User;
import com.example.teammates.MainActivity;
import com.example.teammates.R;
import com.example.teammates.okhttp.ExchangeMessage;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText ori_pass;
    private EditText new_pass;
    private EditText confirm_pass;

    Button commit;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        commit=(Button)findViewById(R.id.change_commit);
        ori_pass=(EditText)findViewById(R.id.ori_pass);//暂时用不到，
        new_pass=(EditText)findViewById(R.id.new_pass);
        confirm_pass=(EditText)findViewById(R.id.confirm_pass);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(new_pass.getText().toString().equals(confirm_pass.getText().toString())){
                    User user=new User();
                    user.setPassword(new_pass.getText().toString());
                    user.updateAll("name = ?",user.getName());
                    //还差  向后台消息的 代码
                    ExchangeMessage.postChange(new_pass.getText().toString(), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Intent intent=new Intent(ChangePasswordActivity.this,MainActivity.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ChangePasswordActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                                }
                            });
                            startActivity(intent);
                        }
                    });

                }else{
                    Toast.makeText(ChangePasswordActivity.this,"原密码错误或两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
