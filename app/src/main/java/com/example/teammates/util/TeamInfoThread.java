package com.example.teammates.util;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Echo on 2018/4/13.
 */

public class TeamInfoThread implements Runnable {

    public static final String TAG = CompetitionInfoThread.class.getName();

    private Context mContext;
    private Handler mHandler;
    private String[] args;

    public TeamInfoThread(Context context, Handler handler,String[] args){
        mContext = context;
        mHandler = handler;
        this.args = args;
    }

    @Override
    public void run(){
        try{
            BaseHttpClient client = new BaseHttpClient();
            SortedMap<String, String> map = new TreeMap<>();
            map.put("competitionName",args[0]);
            String result = client.post("http://106.14.199.25:9091/teamInfo/browseTeamInfo/byCompetitionName",map);
            sendMessage(2,result);
        }catch (Exception e){
            Log.d(TAG,e.getMessage());
            sendMessage(0,"Failure");
        }

    }

    private void sendMessage(int what, Object object) {
        Message message = mHandler.obtainMessage();
        message.what = what;
        message.obj = object;
        mHandler.sendMessage(message);
    }

}
