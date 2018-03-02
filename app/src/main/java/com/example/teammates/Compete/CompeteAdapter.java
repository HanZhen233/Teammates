package com.example.teammates.Compete;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teammates.R;

import java.util.List;

/**
 * Created by Echo on 2018/2/27.
 */

public class CompeteAdapter extends RecyclerView.Adapter<CompeteAdapter.ViewHolder> {
    private Context mContext;
    private List<Compete> competes;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        View competeView;
        TextView competeName;
        ImageView competeImage;

        public ViewHolder(View view) {
            super(view);
            cardView=(CardView) view;
            competeView=view;
            competeName=(TextView) view.findViewById(R.id.compete_name);
            competeImage=(ImageView) view.findViewById(R.id.compete_image);
        }
    }

    public CompeteAdapter(List<Compete> competeList){
        competes=competeList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.compete_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Compete compete=competes.get(position);
                Intent intent=new Intent(mContext,CompeteDetailActivity.class);
                intent.putExtra(CompeteDetailActivity.Compete_Name,compete.getClass());
                intent.putExtra(CompeteDetailActivity.Compete_Image_Id,compete.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Compete compete=competes.get(position);
        holder.competeName.setText(compete.getName());
        Glide.with(mContext).load(compete.getImageId()).into(holder.competeImage);
//        holder.competeImage.setImageResource(compete.getImageId());
    }



    @Override
    public int getItemCount() {
        return competes.size();
    }
}















