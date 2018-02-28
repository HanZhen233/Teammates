package com.example.teammates.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teammates.LoginActivity;
import com.example.teammates.R;
import com.example.teammates.recycler_item.InfoAdapter;
import com.example.teammates.recycler_item.Information;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class MeFragment extends Fragment {
    private View view;

    private TextView status;

    private List<Information> info=new ArrayList<>();

    private void initInfo(){
        Information information=new Information();
        information.setName("个人信息");
        info.add(information);

        Information information1=new Information();
        information1.setName("设置");
        info.add(information1);

        Information information2=new Information();
        information2.setName("关于我们");
        info.add(information2);

        Information information3=new Information();
        information3.setName("凑");
        info.add(information3);

        Information information4=new Information();
        information4.setName("字");
        info.add(information4);

        Information information5=new Information();
        information5.setName("数");
        info.add(information5);

        Information information6=new Information();
        information6.setName("的");
        info.add(information6);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        ImageView user=(ImageView)view.findViewById(R.id.image_icon);
        status=(TextView)view.findViewById(R.id.status);


        initInfo();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        InfoAdapter adapter=new InfoAdapter(info);
        recyclerView.setAdapter(adapter);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
