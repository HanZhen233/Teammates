package com.example.teammates.recycler_item;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.teammates.Data.DataSelfActivity;
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
        holder.itemName.setText(information.getName());
        holder.InfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), DataSelfActivity.class);
                intent.putExtra("key",information.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInfoList.size();
    }

   static class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemName;
        View InfoView;

        public ViewHolder(View view){
            super(view);
            InfoView=view;
            itemName=(TextView)view.findViewById(R.id.item_name);

        }
    }
}
