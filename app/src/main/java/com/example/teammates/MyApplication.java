package com.example.teammates;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by linji on 2018/4/20.
 */

public class MyApplication extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        LitePalApplication.initialize(context);
    }

    public static Context getContext(){
        return  context;
    }
}
