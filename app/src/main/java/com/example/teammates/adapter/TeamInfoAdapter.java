package com.example.teammates.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teammates.R;
import com.example.teammates.db.teamInfo.TeamInfo;

import java.util.List;

/**
 * Created by Echo on 2018/4/11.
 * 找队友
 */

public class TeamInfoAdapter extends RecyclerView.Adapter<TeamInfoAdapter.ViewHolder> {
    private Context mContext;
    private List<TeamInfo> mteamList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView user_name;
        TextView edit_time;
        TextView content;


        public ViewHolder(View view) {
            super(view);
            cardView=(CardView) view;
            user_name=(TextView) view.findViewById(R.id.initiator);
            edit_time=(TextView) view.findViewById(R.id.edit_time);
            content=(TextView) view.findViewById(R.id.content);
        }
    }
    public TeamInfoAdapter(List<TeamInfo> teamList){
        mteamList=teamList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext)
                .inflate(R.layout.team_info,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TeamInfo team=mteamList.get(position);
        //填充内容
        holder.user_name.setText(team.getInitiator());
        holder.edit_time.setText(team.getTime());
        holder.content.setText(team.getIntroduction());
    }

    @Override
    public int getItemCount() {
        return mteamList.size();
    }
}
