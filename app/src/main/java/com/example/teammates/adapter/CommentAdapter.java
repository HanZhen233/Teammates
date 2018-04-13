package com.example.teammates.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.teammates.R;

import com.example.teammates.db.commentInfo.CommentInfo;
import java.util.List;

/**
 * Created by Echo on 2018/4/11.
 * 评论
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<CommentInfo> mComment;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView edit_time;
        TextView content;

        public ViewHolder(View view) {
            super(view);
            user_name=(TextView) view.findViewById(R.id.user_name);
            edit_time=(TextView) view.findViewById(R.id.edit_time);
            content=(TextView) view.findViewById(R.id.content);
        }
    }
    public CommentAdapter(List<CommentInfo> commentList){
        mComment=commentList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userfind_comment,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentInfo comment=mComment.get(position);
        //填充内容
        holder.user_name.setText(comment.getCommentator());
        holder.edit_time.setText(comment.getTime());
        holder.content.setText(comment.getCommentInfo());
    }

    @Override
    public int getItemCount() {
        return mComment.size();
    }
}
