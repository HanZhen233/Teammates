package com.example.teammates.Compete;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.teammates.R;
import com.example.teammates.comment.Comment;
import com.example.teammates.comment.CommentAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CompetitionDetailActivity extends AppCompatActivity {
    public static final String Compete_Name="compete_name";
    public static final String Compete_Image_Id="compete_image_id";
    private List<Comment> commentList=new ArrayList<>();
    private TextView competeContentText;
    String competitionName;
    int competeImageId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compete_detail);
        Intent intent=getIntent();

        competitionName=intent.getStringExtra(Compete_Name);//比赛名称
        competeImageId=intent.getIntExtra(Compete_Image_Id,0);//图片id

        Toolbar toolbar=(Toolbar) findViewById(R.id.detail_toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView competeImage=(ImageView) findViewById(R.id.compete_image_view);
        competeContentText=(TextView) findViewById(R.id.compete_content_text);
        setSupportActionBar(toolbar);
        ActionBar actioinBar=getSupportActionBar();
        if(actioinBar!=null){
            actioinBar.setDisplayHomeAsUpEnabled(true);
            collapsingToolbar.setTitle(competitionName);
        }
        Glide.with(this).load(competeImageId).into(competeImage);
        String competeContent=generateCompeteContent(competitionName,competeImageId);
//        competeContentText.setText(competeContent);

        //设置评论
        initComment();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.comment_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CommentAdapter adapter=new CommentAdapter(commentList);
        recyclerView.setAdapter(adapter);

        //接收比赛信息介绍
        sendrequestWithOkHttp();
    }

    private String generateCompeteContent(String competeName,int competeImageId){
        return "编号为"+competeImageId+"\n"+competeName+"的介绍";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initComment(){
        for(int i=0;i<3;i++){
            Comment comment=new Comment("张三","2018/4/11","寻找一个擅长PS的队友。凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字数凑字");
            commentList.add(comment);
        }
    }
    private void sendrequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("name",competitionName)
                            .build();
                    Request request=new Request.Builder()
                            .url("http://106.14.199.25:9091/competition/ContentByname")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();

                    String content=parseJSONWithGSON(responseData);
                    showResponse(content);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                competeContentText.setText(response);
            }
        });
    }
    private String parseJSONWithGSON(String jsonDate){
        String name="";
        Gson gson=new Gson();
        Competition2 competition = gson.fromJson(jsonDate, Competition2.class);
        return competition.getName()+"\n"+
            "级别：" + competition.getLevel() + "\n" +
            "主办方：" + competition.getHost() + "\n" +
            "比赛时间：" + competition.getTime() + "\n" +"\n"+
            "比赛介绍："+"\n" + competition.getIntroduction() + "\n" +"\n"+
            "比赛链接：" + competition.getLink();

    }
}


