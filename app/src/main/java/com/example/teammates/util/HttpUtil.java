package com.example.teammates.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Echo on 2018/4/13.
 * 和服务器交互，注册回调处理服务器响应
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
