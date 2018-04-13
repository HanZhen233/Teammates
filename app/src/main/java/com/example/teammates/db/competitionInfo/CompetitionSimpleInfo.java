package com.example.teammates.db.competitionInfo;

/**
 * Created by Echo on 2018/2/27.
 */

public class CompetitionSimpleInfo {
    private String name;
    private int imageId;

    public CompetitionSimpleInfo(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public int getImageId(){
        return imageId;
    }
    public void setImageId(int imageId){
        this.imageId=imageId;
    }

}
