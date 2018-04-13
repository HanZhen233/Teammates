package com.example.teammates.Data;

import org.litepal.crud.DataSupport;

/**
 * Created by linji on 2018/3/23.
 */

public class Message extends DataSupport {
    public String title;
    public User message_sender;
    public String answer;


    public String getTitle() {
        return title;
    }

    public User getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(User message_sender) {
        this.message_sender = message_sender;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
