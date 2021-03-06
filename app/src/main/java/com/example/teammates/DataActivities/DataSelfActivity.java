package com.example.teammates.DataActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teammates.ActivityCollector;
import com.example.teammates.db.user.User;
import com.example.teammates.R;
import com.example.teammates.RegisterActivity;
import com.example.teammates.okhttp.ExchangeMessage;

import org.litepal.crud.DataSupport;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class DataSelfActivity extends AppCompatActivity {

   // private LinearLayout self_information;//布局文件
//
    private TextView name_information;
    private TextView sex_information;
    private TextView university_information;
    private TextView major_information;
    private TextView technology_information;
    private TextView phone_information;
    private TextView qq_information;
    private TextView email_information;

    private Button update;

    private void initView(){
        //self_information=(LinearLayout)findViewById(R.id.self_information);//布局文件

        name_information=(TextView)findViewById(R.id.name);
       // pass_information=(TextView)findViewById(R.id.password);
        sex_information=(TextView)findViewById(R.id.sex) ;
        university_information=(TextView)findViewById(R.id.university);
        major_information=(TextView)findViewById(R.id.major);
        technology_information=(TextView)findViewById(R.id.technology);
        phone_information=(TextView)findViewById(R.id.phone);
        qq_information=(TextView)findViewById(R.id.qq);

        email_information=(TextView)findViewById(R.id.email);

        update=(Button)findViewById(R.id.update);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself);

        initView();
        final Intent intent=getIntent();
        String content=intent.getStringExtra("key");

        switch (content){
            case "个人信息":



            User user = DataSupport.findFirst(User.class);
            if(user==null) break;
            name_information.setText(user.getName());

            sex_information.setText(user.getSex());
            university_information.setText(user.getUniversity());
            major_information.setText(user.getMajor());
            technology_information.setText(user.getTechnology());
            phone_information.setText(user.getPhone());
            qq_information.setText(user.getQq());

            email_information.setText(user.getEmail());
                Log.d("mail", email_information.getText().toString());
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1=new Intent(DataSelfActivity.this, RegisterActivity.class);
                    startActivity(intent1);
                }
            });

            break;
            case "修改密码":
                Intent intent1=new Intent(getBaseContext(),ChangePasswordActivity.class);
                startActivity(intent1);
                break;
            case "注销":
                //status.setText("未登录");  属于更改 其他活动中的ui元素
                //状态检测  还没做

                ExchangeMessage.getLogout(new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        DataSelfActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DataSelfActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        DataSelfActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(DataSelfActivity.this,"注销成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });


                DataSupport.deleteAll(User.class);
                name_information.setText("");
                sex_information.setText("");
                university_information.setText("");
                major_information.setText("");
                technology_information.setText("");
                phone_information.setText("");
                qq_information.setText("");
                email_information.setText("");
                ActivityCollector.finishAll();


                break;

        }


    }

}
