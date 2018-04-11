package com.example.teammates.Compete;

/**
 * Created by Echo on 2018/2/27.
 */

public class Competition {
    private String name;
    private int imageId;

    public Competition(String name, int imageId){
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
