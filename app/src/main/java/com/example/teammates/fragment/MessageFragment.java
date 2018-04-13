package com.example.teammates.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teammates.R;
import com.example.teammates.okhttp.ExchangeMessage;
//import com.example.teammates.recycler_item.InfoAdapter;
//import com.example.teammates.recycler_item.Information;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class MessageFragment extends Fragment {
    private View view;
//    private List<Information> info=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_message,container,false);

       // ExchangeMessage.getMessage();//发送信息到后台 获取本用户的所有消息，


        return view;
    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if(info.size()>=2){
//            //这里用于应付  随着碎片不断切换而 增长的 recycler_view  暂时的
//        }
//        else{
//            initInfo();
//        }
//
//        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        InfoAdapter adapter=new InfoAdapter(info);
//        recyclerView.setAdapter(adapter);
//
//    }
//
//
//    private void initInfo(){
//        Information information=new Information();
//        information.setContent("this is a test");
//        information.setImageId(R.drawable.touxiang);
//        info.add(information);
//
//        Information i1=new Information();
//        i1.setContent("test 2");
//        i1.setImageId(R.drawable.background);
//        info.add(i1);
//
//        //需要每次点击这个都要  发送获取消息的
//    }
//

}