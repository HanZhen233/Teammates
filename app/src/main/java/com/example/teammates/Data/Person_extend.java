package com.example.teammates.Data;

import org.litepal.crud.DataSupport;

/**
 * Created by linji on 2018/3/23.
 */

public class Person_extend extends DataSupport {
    private int team_number;//已组多少队
    private int praise_number;//获赞
    private int bad_number;//被 评不好
    private String evaluate;//队友评价

    public int getTeam_number() {
        return team_number;
    }

    public void setTeam_number(int team_number) {
        this.team_number = team_number;
    }

    public int getPraise_number() {
        return praise_number;
    }

    public void setPraise_number(int praise_number) {
        this.praise_number = praise_number;
    }

    public int getBad_number() {
        return bad_number;
    }

    public void setBad_number(int bad_number) {
        this.bad_number = bad_number;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }
}
