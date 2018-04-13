package com.example.teammates.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teammates.Compete.Competition1;
import com.example.teammates.Compete.CompetitionAdapter;
import com.example.teammates.HomeSetting.HomeSearchActivity;
import com.example.teammates.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class HomeFragment extends Fragment {
    private View view;
    private List<Competition1> competeList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private TextView response_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toolbar();
//        if(competeList.isEmpty())
//            sendrequestWithOkHttp();//发送get请求获取比赛信息
//        else{
//            competeList.clear();
//            sendrequestWithOkHttp();//发送get请求获取比赛信息
//        }
        CompeteRecyclerView();
        refresh();
        refreshCompete();
        sendrequestWithOkHttp();
//        final Button sendrequest=(Button) getActivity().findViewById(R.id.send_require);
//        response_text=(TextView) getActivity().findViewById(R.id.response_text);
//        sendrequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendrequestWithOkHttp();
//                Toast.makeText(getActivity(),"lalala",Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void Toolbar(){
        Toolbar toolbar=(Toolbar) getActivity().findViewById(R.id.home_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        ImageView imageView=(ImageView) getActivity().findViewById(R.id.home_toolbar_searchPic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), HomeSearchActivity.class);
                startActivity(intent);
            }
        });
    }


    //设置滚动图片
    private void CompeteRecyclerView(){
        RecyclerView recyclerView=(RecyclerView) getActivity().findViewById(R.id.home_recyclerview);
        GridLayoutManager layoutManager=new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        CompetitionAdapter adapter=new CompetitionAdapter(competeList);
        recyclerView.setAdapter(adapter);
    }

    private void refresh(){
        swipeRefresh=(SwipeRefreshLayout) getActivity().findViewById(R.id.home_swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshCompete();
            }
        });
    }

    private void refreshCompete(){
        //新增比赛项目
        swipeRefresh.setRefreshing(false);

    }

    private void sendrequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://106.14.199.25:9091/competition/simpleContentAll")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSONWithGSON(responseData);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initCompete(String name){
        Competition1 competition=new Competition1(name,R.drawable.p1);
        competeList.add(competition);
    }

    private void parseJSONWithGSON(String jsonDate){
        Gson gson=new Gson();
        List<Competition1> competitionList=gson.fromJson(jsonDate,new TypeToken<List<Competition1>>()
        {}.getType());
        String x="";
        for(Competition1 competition:competitionList){
            initCompete(competition.getName());
            x+=competition.getName();
            System.out.print(competition.getName());
        }
        showResponse(x);
    }


    private void showResponse(final String response){
        getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
//                response_text.setText(response);
            }
        });
    }
}
