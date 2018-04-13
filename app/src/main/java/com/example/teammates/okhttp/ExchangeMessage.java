package com.example.teammates.okhttp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;


import com.example.teammates.db.user.User;
import com.example.teammates.RegisterActivity;

import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
;


import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
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

    public static Map<String,List<Cookie>> cookiesMap=new HashMap<String,List<Cookie>>();//只够登陆一次用的。。
    public static CookieJar cookiejar;
    public static void postLogin(String account,final Context context, String password, okhttp3.Callback callback){


        CookieJar cookieJar = new CookieJar() {
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                StringBuilder sb=new StringBuilder();
                SharedPreferences.Editor editor=context.getSharedPreferences("cookies",Context.MODE_PRIVATE).edit();
                for(Cookie cookie:cookies){
                    sb.append(cookie.name()+"="+cookie.value()+";");
                }
                editor.putString("cookies",sb.toString());
                editor.apply();
                cookiesMap.put(url.host(),cookies);

            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
                String cookies=preferences.getString("cookies","");
                String[]cookie=cookies.split(";");


                List<Cookie> cookieList=cookiesMap.get(url.host());

                return cookieList != null ? cookieList : new ArrayList<Cookie>();
            }
            //存储数据

        };
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();
        RequestBody requestBody=new FormBody.Builder()//用json来传递键值
                .add("username",account)
                .add("password",password)
                .build();
        Request request=new Request.Builder()
                .url(URL.post_login)
                .post(requestBody)
                .build();
        //cookiejar=cookieJar;
        //Log.d("cookie", cookieJar.toString());
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
        OkHttpClient client=new OkHttpClient.Builder().cookieJar(cookiejar).build();

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
        OkHttpClient client=new OkHttpClient.Builder().build();
        Request request=new Request.Builder()
                .url(URL.get_logout)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
