package com.example.teammates.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.teammates.DataActivities.DataSelfActivity;
import com.example.teammates.LoginActivity;
import com.example.teammates.R;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class MeFragment extends Fragment implements View.OnClickListener{
    private View view;

    private Button self;
    private Button log_out;
    private Button change;
    private Button login;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_new_me,container,false);

        self=(Button)view.findViewById(R.id.self_informatino);
        log_out=(Button)view.findViewById(R.id.log_out);
        change=(Button)view.findViewById(R.id.change_password);
        login=(Button)view.findViewById(R.id.login);

        self.setOnClickListener(this);
        log_out.setOnClickListener(this);
        change.setOnClickListener(this);
        login.setOnClickListener(new View.OnClickListener() {
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
            case R.id.self_informatino:
                intent.putExtra("key","个人信息");
                break;
            case R.id.log_out:
                intent.putExtra("key","注销");

                break;
            case R.id.change_password:
                intent.putExtra("key","修改密码");
        }
        startActivity(intent);

    }
}
