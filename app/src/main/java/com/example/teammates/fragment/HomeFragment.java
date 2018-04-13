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


import com.example.teammates.adapter.CompetitionAdapter;
import com.example.teammates.competition.CompetitionSearchActivity;
import com.example.teammates.R;
import com.example.teammates.db.competitionInfo.CompetitionSimpleInfo;
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
    private List<CompetitionSimpleInfo> competeList=new ArrayList<>();
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
        CompeteRecyclerView();
        refresh();
        refreshCompete();
        sendrequestWithOkHttp();
    }

    private void Toolbar(){
        Toolbar toolbar=(Toolbar) getActivity().findViewById(R.id.home_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        ImageView imageView=(ImageView) getActivity().findViewById(R.id.home_toolbar_searchPic);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CompetitionSearchActivity.class);
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
        CompetitionSimpleInfo competition=new CompetitionSimpleInfo(name,R.drawable.p1);
        competeList.add(competition);
    }

    private void parseJSONWithGSON(String jsonDate){
        Gson gson=new Gson();
        List<CompetitionSimpleInfo> competitionList=gson.fromJson(jsonDate,new TypeToken<List<CompetitionSimpleInfo>>()
            {}.getType());
        String x="";
        for(CompetitionSimpleInfo competition:competitionList){
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
