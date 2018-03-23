package com.example.teammates.Data;

import org.litepal.crud.DataSupport;

/**
 * Created by linji on 2018/3/23.
 */

public class Competition extends DataSupport {
    public String title;
    public String synopsis;//简介
    //暂时就这么简略的写
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
