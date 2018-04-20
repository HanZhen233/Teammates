package com.example.teammates.okhttp;

import android.content.Context;

import android.support.annotation.NonNull;
import android.util.Log;


import com.example.teammates.MyApplication;
import com.example.teammates.db.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by linji on 2018/4/12.
 */

public class ExchangeMessage {
//@NonNull非空标注
    private final static OkHttpClient client=new OkHttpClient.Builder()
        .followRedirects(false)
        .followSslRedirects(false)
        .cookieJar(new LocalCookieJar())
        .build();

     static class LocalCookieJar implements CookieJar {

        PersistentCookieStore cookieStore=new PersistentCookieStore(MyApplication.getContext());
        @Override
        public List<Cookie> loadForRequest(HttpUrl arg0) {
           List<Cookie> cookies=cookieStore.get(arg0);
           return  cookies;
        }

        @Override
        public void saveFromResponse(HttpUrl arg0, List<Cookie> cookies) {
            if(cookies!=null&&cookies.size()>0){
                for(Cookie item:cookies){
                    cookieStore.add(arg0,item);
                }
            }
        }

    }

    public static void getMessage(String url,okhttp3.Callback callback){

        Request request=new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postRegister(String url,User user,okhttp3.Callback callback){
      //  OkHttpClient client=new OkHttpClient();
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



    public static void postLogin(String account,final Context context, String password, okhttp3.Callback callback){

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
        Request request=new Request.Builder()
                .url(URL.get_UserInformation)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void postChange(String password,okhttp3.Callback callback){

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
         Request request=new Request.Builder()
                .url(URL.get_logout)
                .build();
        client.newCall(request).enqueue(callback);
    }


}
