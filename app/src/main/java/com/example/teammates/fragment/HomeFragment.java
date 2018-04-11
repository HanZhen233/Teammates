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

import com.example.teammates.Compete.Competition;
import com.example.teammates.Compete.CompetitionAdapter;
import com.example.teammates.HomeSetting.HomeSearchActivity;
import com.example.teammates.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class HomeFragment extends Fragment {
    private View view;
    private List<Competition> competeList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
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
        if(competeList.isEmpty())
            initCompete();
        else{
            competeList.clear();
            initCompete();
        }
        CompeteRecyclerView();
        refresh();
        refreshCompete();
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
    private void initCompete(){

            Competition compete=new Competition("中国计算机设计大赛",R.drawable.p1);
            for(int i=0;i<20;i++){
                competeList.add(compete);
            }


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
}
