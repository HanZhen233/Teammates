package com.example.teammates.Compete;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.teammates.R;
import com.example.teammates.comment.TeamInfo;
import com.example.teammates.comment.RequireComAdapter;
import com.example.teammates.comment.TeamInfo;
import com.example.teammates.comment.TeamInfoComment;
import com.example.teammates.util.CompetitionInfoThread;
import com.example.teammates.util.HttpUtil;
import com.example.teammates.util.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CompetitionDetailActivity extends AppCompatActivity {
    public static final String Compete_Name="compete_name";
    public static final String Compete_Image_Id="compete_image_id";
    private List<TeamInfo> commentList=new ArrayList<>();
    private TextView competeContentText;
    String competitionName;
    int competeImageId;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private List<TeamInfo> teamInfoList;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:{
                    competeContentText.setText("Failure");
                }
                case 1:{
                    competeContentText.setText("Success");
                }
                case 2:{

                }
                case 3:{

                }
            }

        };
    };
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

        //接收比赛信息介绍
        //sendrequestWithOkHttp();
        //接收比赛队伍信息
        //Log.d("Competition","Detail3");
        //queryTeams();
        //Log.d("Competition","Detail4");
//        competeContentText.setText(competeContent);

        //设置评论
//        initComment();


        recyclerView=(RecyclerView) findViewById(R.id.comment_view);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RequireComAdapter adapter=new RequireComAdapter(commentList);
        recyclerView.setAdapter(adapter);

        //悬浮按钮
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CompetitionDetailActivity.this,AddFindInfo.class);
                startActivity(intent1);
            }
        });



        CompetitionInfoThread competitionInfoThread = new CompetitionInfoThread(this,handler,null);
        new Thread(competitionInfoThread).start();

    }
    private  void queryTeams(){
        teamInfoList= DataSupport.findAll(TeamInfo.class);
        if(teamInfoList.size()>0){
            commentList.clear();
            Log.d("Competition","Detail1");
            for (TeamInfo team:teamInfoList){
                commentList.add(team);
                Log.d("Competition","Detail2");
                Log.d("证明",team.gettTamInfoComments()+team.getCompetitionName());
            }
        }else{
            String address="http://106.14.199.25:9091/teamInfo/browseTeamInfo";
            queryFormServer(address);
        }
    }
    private void queryFormServer(String address){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText=response.body().string();
                boolean result=false;
                result= Utility.handleProvinceResponse(responseText);
                if (result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            queryTeams();
                        }
                    });
                }
            }
        });
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
    private void initComment(TeamInfo requireCom){
//        for(int i=0;i<3;i++){
//            List<TeamInfoComment> comment=new ArrayList<>();
//            TeamInfoComment team=new TeamInfoComment("1","content","Echo","Time");
//            comment.add(team);
//            TeamInfo requireCom=new TeamInfo("计算机","国家","110","1","Echo","内容","anqiu",comment,"1","1");
            commentList.add(requireCom);
//        }
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


