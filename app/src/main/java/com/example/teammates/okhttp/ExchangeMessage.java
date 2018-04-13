package com.example.teammates.okhttp;

import android.util.Log;


import com.example.teammates.db.user.User;
import com.example.teammates.RegisterActivity;

import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
;


import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by linji on 2018/4/12.
 */

public class ExchangeMessage {

    public static void getMessage(String url,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");//设置Content-Type 标头中包含的媒体类型值

    public static void postMessage(String url,String json,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=FormBody.create(JSON,json);//用json来传递键值

        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void postRegister(String url,User user,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Log.d("register", user.getName());
        RequestBody requestBody=new FormBody.Builder()//用json来传递键值
                .add("username",user.getName())
                .add("password",user.getPassword())
                .add("sex",user.getSex())
                .add("university",user.getUniversity())
                .add("major",user.getMajor())
                .add("technology",user.getTechnology())
                .add("phone",user.getPhone())
                .add("qq",user.getQq())
                .add("mail",user.getMajor())
                .build();
        Request request=new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postLogin(String account,String password,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()//用json来传递键值
                .add("username",account)
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .url(URL.post_login)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void getLogin(okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder()
                .url(URL.get_UserInformation)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postChange(String password,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody=new FormBody.Builder()//用json来传递键
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .url(URL.post_changePassword)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }


    public static void getLogout(okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder()
                .url(URL.get_logout)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
