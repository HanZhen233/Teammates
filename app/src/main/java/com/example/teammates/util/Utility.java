package com.example.teammates.util;

import android.text.TextUtils;

import com.example.teammates.db.commentInfo;
import com.example.teammates.db.teamInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Echo on 2018/4/13.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allTeamInfo=new JSONArray(response);
                for(int i=0;i<allTeamInfo.length();i++){
                    JSONObject teamInfoObject=allTeamInfo.getJSONObject(i);
                    teamInfo teaminfo=new teamInfo();
                    teaminfo.setCompetitionLevel(teamInfoObject.getString("competitionLevel"));
                    teaminfo.setCompetitionName(teamInfoObject.getString("competitionName"));
                    teaminfo.setConnection(teamInfoObject.getString("connection"));
                    teaminfo.setId(teamInfoObject.getString("id"));
                    teaminfo.setInitiator(teamInfoObject.getString("initiator"));
                    teaminfo.setIntroduction(teamInfoObject.getString("introduction"));
                    teaminfo.setIntroduction(teamInfoObject.getString("requirement"));
                    teaminfo.setTime(teamInfoObject.getString("time"));
                    teaminfo.setUniversity(teamInfoObject.getString("university"));
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
