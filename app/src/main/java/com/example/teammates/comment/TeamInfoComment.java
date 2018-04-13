package com.example.teammates.comment;

import java.io.Serializable;

public class TeamInfoComment implements Serializable {
    private  String id;
    private  String commentInfo;
    private  String commentator;
    private  String time;
    public TeamInfoComment(String id,String commentInfo, String commentator, String time){
        this.id=id;
        this.commentInfo=commentInfo;
        this.commentator=commentator;
        this.time=time;
    }
    public  TeamInfoComment(){
    }
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
