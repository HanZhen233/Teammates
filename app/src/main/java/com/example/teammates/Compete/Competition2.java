package com.example.teammates.Compete;

/**
 * Created by Echo on 2018/2/27.
 */

public class Competition2 {
    private String name;
    private String level;
    private String host;
    private String time;
    private String introduction;
    private String link;


    public Competition2(String name, String level, String host, String time, String introduction, String link){
        this.name=name;
        this.level=level;
        this.host=host;
        this.time=time;
        this.introduction=introduction;
        this.link=link;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }


    public String getHost(){return host;}
    public void setHost(String host){this.host=host;}

    public String getLevel(){return level;}
    public void setLevel(String level){this.level=level;}

    public String getTime(){return time;}
    public void setTime(String time){this.time=time;}

    public String getIntroduction(){return introduction;}
    public void setIntroduction(String introduction){this.introduction=introduction;}

    public String getLink(){return link;}
    public void setLink(String link){this.link=link;}
}
