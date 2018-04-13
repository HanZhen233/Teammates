package com.example.teammates.okhttp;

/**
 * Created by linji on 2018/4/13.
 */

public class URL {
    /*
    * get  请求
    * */
    public final static String get_logout="http://106.14.199.25:9091/logout";
    public final static String get_AllCompetitionName="http://106.14.199.25:9091/competition/simpleContentAll";
    public final static String get_browse_Team="http://106.14.199.25:9091/teamInfo/browseTeamInfo";
    public final static String get_loginSuccess="http://http://106.14.199.25:9091/login1";
    //登陆的时候 才能get
    public final static String get_UserInformation="http://106.14.199.25:9091/logined";

    /*
    * post 请求
    * */

    public final static String post_Competition_detail_byName="http://106.14.199.25:9091/competition/ContentByname";//post 字段为 name
    public final static String post_Competition_detail_byLevel="http://106.14.199.25:9091/competition/ContentByLevel";//post level
    public final static String post_register="http://106.14.199.25:9091/addUser";//post User类的所有字段
    public final static String post_search_Team_byLevel="http://106.14.199.25:9091/teamInfo/browseTeamInfo/byCompetitionLevel";//post competitionLevel
    public final static String post_search_Team_byName="http://106.14.199.25:9091/teamInfo/browseTeamInfo/byCompetitionName";//post competitionName
    public final static String post_search_Team_byUniversity="http://106.14.199.25:9091/teamInfo/browseTeamInfo/byUniversity";//post university
    public final static String post_changePassword="http://106.14.199.25:9091/changePassword";
    public final static String post_login="http://106.14.199.25:9091/login";//post username password

    //登陆状态下才能 做
    public final static String post_createTeam="http://106.14.199.25:9091/teamInfo/addTeamInfo";
    public final static String post_comment="http://106.14.199.25:9091/teamInfo/addTeamComment";


}
