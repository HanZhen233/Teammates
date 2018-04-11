package com.example.teammates.comment;

/**
 * Created by Echo on 2018/4/11.
 */

public class Comment {
    private String name;//评论用户
    private String time;//评论时间
    private String content;//内容
    private String link;//
    private String find;//‘找队友’或‘找队伍’
    public Comment(String name,String time,String content){
        this.name=name;
        this.time=time;
        this.content=content;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time=time;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link=link;
    }
}
