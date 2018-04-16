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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

public class CompetitionDetailActivity extends AppCompatActivity {
    public static final String Compete_Name="compete_name";
    public static final String Compete_Image_Id="compete_image_id";
    private List<TeamInfo> teamInfoList=new ArrayList<>();
    private TextView competeContentText;
    private String competitionName=null;
    private int competeImageId;
    private TeamInfoAdapter adapter;
    private String TAG="CompetitionDetail";
    private RecyclerView recyclerView;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:{
                    competeContentText.setText("Failure");
                }
                case 1:{
                    String competitionWholeInfo=(String) msg.obj;
                    competeContentText.setText(parseJSONCompetition(competitionWholeInfo));
                }
                case 2:{
                    String teamInfo=(String) msg.obj;
                    parseJSONTeamInfo(teamInfo);
                    adapter=new TeamInfoAdapter(teamInfoList);
                    recyclerView.setAdapter(adapter);
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

        ImageView competeImage=(ImageView) findViewById(R.id.compete_image_view);
        competeContentText=(TextView) findViewById(R.id.compete_content_text);
        competitionName=intent.getStringExtra(Compete_Name);//比赛名称
        competeImageId=intent.getIntExtra(Compete_Image_Id,0);//图片id

        Toolbar toolbar=(Toolbar) findViewById(R.id.detail_toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actioinBar=getSupportActionBar();
        if(actioinBar!=null){
            actioinBar.setDisplayHomeAsUpEnabled(true);
            collapsingToolbar.setTitle(competitionName);
        }
        Glide.with(this).load(competeImageId).into(competeImage);

        //队伍的卡片布局
        recyclerView=(RecyclerView) findViewById(R.id.team_recyclerview);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

        //悬浮按钮
        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(CompetitionDetailActivity.this,AddTeamActivity.class);
                startActivity(intent1);
            }
        });

        //向服务器发送请求
        String[] array={competitionName};
        TeamInfoThread teamInfoThread = new TeamInfoThread(this,handler,array);
        new Thread(teamInfoThread).start();
        CompetitionInfoThread competitionInfoThread = new CompetitionInfoThread(this,handler,array);
        new Thread(competitionInfoThread).start();
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
        Log.d(TAG+"parseJ",jsonDate);
        try{
            JSONArray jsonArray=new JSONArray(jsonDate);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String competitionName=jsonObject.getString("competitionName");
                String competitionLevel=jsonObject.getString("competitionLevel");
                String university=jsonObject.getString("university");
                String introduction=jsonObject.getString("introduction");
                String time=jsonObject.getString("time");
                String initiator=jsonObject.getString("initiator");
                String requirement=jsonObject.getString("requirement");
                String connection=jsonObject.getString("connection");
                TeamInfo teamInfo=new TeamInfo(id,competitionName,competitionLevel,time,university
                        ,introduction,initiator,requirement,connection);
                teamInfoList.add(teamInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        int size=teamInfoList.size();
        Log.d(TAG, String.valueOf(size));
    }
}


