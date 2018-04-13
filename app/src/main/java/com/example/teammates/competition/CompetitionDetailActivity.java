package com.example.teammates.competition;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.teammates.R;
import com.example.teammates.db.teamInfo.TeamInfo;
import com.example.teammates.adapter.TeamInfoAdapter;
import com.example.teammates.db.competitionInfo.CompetitionWholeInfo;
import com.example.teammates.util.CompetitionInfoThread;
import com.example.teammates.util.TeamInfoThread;
import com.google.gson.Gson;

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
    private List<TeamInfo> teamInfoList=new ArrayList<>();
    private TextView competeContentText;
    private TextView test;
    String competitionName=null;
    int competeImageId;
    LinearLayoutManager layoutManager;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:{
                    competeContentText.setText("Failure");
                }
                case 1:{
                    String competitionWholeInfo=(String) msg.obj;
                    showResponse(parseJSONCompetition(competitionWholeInfo));
                }
                case 2:{
                    String teamInfo=(String) msg.obj;
//                    competeContentText.setText(teamInfo);
                    parseJSONTeamInfo(teamInfo);
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
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.comment_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        TeamInfoAdapter adapter=new TeamInfoAdapter(teamInfoList);
        recyclerView.setAdapter(adapter);

        //悬浮按钮
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CompetitionDetailActivity.this,AddTeamActivity.class);
                startActivity(intent1);
            }
        });


        String[] array={competitionName};

        CompetitionInfoThread competitionInfoThread = new CompetitionInfoThread(this,handler,array);
        new Thread(competitionInfoThread).start();
        TeamInfoThread teamInfoThread = new TeamInfoThread(this,handler,array);
        new Thread(teamInfoThread).start();

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
            teamInfoList.add(requireCom);
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

                    String content=parseJSONCompetition(responseData);
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
    private String parseJSONCompetition(String jsonDate){
        String name="";
        Gson gson=new Gson();
        CompetitionWholeInfo competition = gson.fromJson(jsonDate, CompetitionWholeInfo.class);
        return competition.getName()+"\n"+
                "级别：" + competition.getLevel() + "\n" +
                "主办方：" + competition.getHost() + "\n" +
                "比赛时间：" + competition.getTime() + "\n" +"\n"+
                "比赛介绍："+"\n" + competition.getIntroduction() + "\n" +"\n"+
                "比赛链接：" + competition.getLink();
    }
    private void parseJSONTeamInfo(String jsonDate){
        try{
            JSONArray jsonArray=new JSONArray(jsonDate);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String name=jsonObject.getString("initiator");
                String content=jsonObject.getString("introduction");
                String time=jsonObject.getString("time");
                String competitionName=jsonObject.getString("competitionName");
                TeamInfo teamInfo=new TeamInfo(null,competitionName,null,null,name,
                        content,null,null,time,null);
                teamInfoList.add(teamInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}


