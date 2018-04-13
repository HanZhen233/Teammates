package com.example.teammates.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.teammates.R;
import com.example.teammates.competition.AddTeamActivity;
import com.example.teammates.competition.CompetitionSearchActivity;
import com.example.teammates.search_part.SearchEmployeeActivity;
import com.example.teammates.search_part.SearchEmployerActivity;

/**
 * Created by hanzhen on 2018/2/24.
 */

public class SearchFragment extends Fragment {
    private View view;
    private LinearLayout employer;
    private LinearLayout employee;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_search,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        employer=(LinearLayout) view.findViewById(R.id.Employer);
        employee=(LinearLayout) view.findViewById(R.id.Employee);
        employer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CompetitionSearchActivity.class);
                startActivity(intent);
            }
        });
        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), AddTeamActivity.class);
                startActivity(intent);
            }
        });
    }
}