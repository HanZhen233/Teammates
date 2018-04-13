package com.example.teammates.db.competitionInfo;

/**
 * Created by Echo on 2018/2/27.
 */

public class CompetitionSimpleInfo {
    private String name;
    private int id;

    public CompetitionSimpleInfo(String name, int id){
        this.name=name;
        this.id=id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

}
