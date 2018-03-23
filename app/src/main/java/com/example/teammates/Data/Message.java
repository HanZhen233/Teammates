package com.example.teammates.Data;

import org.litepal.crud.DataSupport;

/**
 * Created by linji on 2018/3/23.
 */

public class Message extends DataSupport {
    public String title;
    public String content;
    public String answer;
    public int imageId;//没有设置头像，默认是姓名的后两字

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
