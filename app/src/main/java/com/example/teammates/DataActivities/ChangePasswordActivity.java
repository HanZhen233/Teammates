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
                    Toast.makeText(ChangePasswordActivity.this,"更改密码成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ChangePasswordActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ChangePasswordActivity.this,"原密码错误或两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
