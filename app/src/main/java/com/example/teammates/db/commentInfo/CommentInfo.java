package com.example.teammates.db.commentInfo;

import org.litepal.crud.DataSupport;

/**
 * Created by Echo on 2018/4/13.
 */

public class CommentInfo extends DataSupport {
    private  String id;
    private  String commentInfo;
    private  String commentator;
    private  String time;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public String getCommentator() {
        return commentator;
    }

    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
