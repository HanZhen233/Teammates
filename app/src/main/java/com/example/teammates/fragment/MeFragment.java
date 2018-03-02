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

import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.example.teammates.Data.DataSelfActivity;
import com.example.teammates.LoginActivity;
import com.example.teammates.R;
import com.example.teammates.recycler_item.InfoAdapter;
import com.example.teammates.recycler_item.Information;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private View view;

    private Button self;
    private Button set;
    private Button us;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_me,container,false);
        self=(Button)view.findViewById(R.id.self);
        set=(Button)view.findViewById(R.id.set);
        us=(Button)view.findViewById(R.id.us);
        ImageView user=(ImageView)view.findViewById(R.id.image_icon);



        self.setOnClickListener(this);
        set.setOnClickListener(this);
        us.setOnClickListener(this);
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

    public void onClick(View v){
        Intent intent=new Intent(getContext(), DataSelfActivity.class);
        switch (v.getId()){
            case R.id.self:
                intent.putExtra("key",self.getText().toString());
                break;
            case R.id.set:
                intent.putExtra("key",set.getText().toString());

                break;
            case R.id.us:
                intent.putExtra("key",us.getText().toString());
                break;

        }
        startActivity(intent);
    }
}
