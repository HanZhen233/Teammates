package com.example.teammates.db.teamInfo;

import com.example.teammates.db.commentInfo.CommentInfo;

import java.util.List;

/**
 * Created by Echo on 2018/4/12.
 * 找队友
 */

public class TeamInfo {
    private String competitionName;
    private String competitionLevel;
    private String connection;
    private String id;
    private String initiator;//发起者
    private String introduction;
    private String requirement;
    private List<CommentInfo> teamInfoComments;
    private String time;//评论时间
    private String university;
    public TeamInfo(String competitionLevel, String competitionName, String connection,
                    String id, String initiator, String introduction, String requirement,
                    List<CommentInfo> teamInfoComments, String time, String university){
        this.competitionName=competitionName;
        this.competitionLevel=competitionLevel;
        this.connection=connection;
        this.id=id;
        this.initiator=initiator;
        this.introduction=introduction;
        this.requirement=requirement;
        this.teamInfoComments=teamInfoComments;
        this.time=time;
        this.university=university;
    }
    public String getCompetitionName(){
        return competitionName;
    }
    public void setCompetitionName(String name){
        this.competitionName=name;
    }

    public String getCompetitionLevel(){
        return competitionLevel;
    }
    public void setCompetitionLevel(String competitionLevel){
        this.competitionLevel=competitionLevel;
    }

    public String getConnection(){
        return connection;
    }
    public void setConnection(String connection){
        this.connection=connection;
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

    public String getRequirement(){
        return requirement;
    }
    public void setRequirement(String requirement){
        this.requirement=requirement;
    }

    public List<CommentInfo> gettTamInfoComments(){
        return teamInfoComments;
    }
    public void setTeamInfoComments(List<CommentInfo> teamInfoComments){
        this.teamInfoComments=teamInfoComments;
    }

    public String getUniversity(){
        return university;
    }
    public void setUniversity(String university){
        this.university=university;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }
}
