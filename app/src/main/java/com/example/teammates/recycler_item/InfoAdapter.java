package com.example.teammates.recycler_item;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.teammates.DataActivities.MessageActivity;
import com.example.teammates.R;

import java.util.List;

/**
 * Created by linji on 2018/2/27.
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    private List<Information> mInfoList;

    private Context context;

    public InfoAdapter(List<Information> InfoLists){
        mInfoList=InfoLists;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.info_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Information information=mInfoList.get(position);
        holder.content.setText(information.getContent());
        //用glide加载 图片
        Glide.with(context).load(information.getImageId()).into(holder.head);
        holder.InfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MessageActivity.class);
                intent.putExtra("head",information.getImageId());
                intent.putExtra("content",information.getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

   static class ViewHolder extends RecyclerView.ViewHolder{

        TextView content;
        View InfoView;
        ImageView head;

        public ViewHolder(View view){
            super(view);
            InfoView=view;
            content=(TextView)view.findViewById(R.id.content);
            head=(ImageView)view.findViewById(R.id.head);
        }
    }
}
