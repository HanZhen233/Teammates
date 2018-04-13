package com.example.teammates.comment;

import com.example.teammates.comment.TeamInfoComment;

import java.util.List;

/**
 * Created by Echo on 2018/4/12.
 * 找队友
 */

public class RequireCom {
    private String initiator;//发起者
    private String introduction;
    private String time;//评论时间

    public RequireCom(String initiator, String introduction,String time){
        this.initiator=initiator;
        this.introduction=introduction;
        this.time=time;
    }

    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }

    public String getInitiator(){
        return initiator;
    }
    public void setInitiator(String initiator){
        this.initiator=initiator;
    }

    public String getIntroduction(){
        return introduction;
    }
    public void setIntroduction(String introduction){
        this.introduction=introduction;
    }
}
