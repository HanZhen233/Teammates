package com.example.teammates.Compete;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.teammates.HomeSetting.HomeSearchActivity;
import com.example.teammates.R;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddFindInfo extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private String inputText;
    private String competitionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfindinfo_activity);
        editText=(EditText) findViewById(R.id.user_need);
        button=(Button) findViewById(R.id.user_need_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputText=editText.getText().toString();
                sendrequestWithOkHttp();
                finish();
            }
        });
        Intent intent=getIntent();
        competitionId=intent.getStringExtra("competitionId");
    }
    private void sendrequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("teamInfoId",competitionId)
                            .add("commentInfo",inputText)
                            .build();
                    Request request=new Request.Builder()
                            .url("http://106.14.199.25:9091/competition/ContentByname")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
