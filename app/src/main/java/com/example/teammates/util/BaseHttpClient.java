package com.example.teammates.util;

/**
 * Created by Echo on 2018/4/13.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by JackYanx on 2018/3/25.
 * Http请求操作类
 */

public class BaseHttpClient {

    private OkHttpClient okHttpClient = null;
    private Request request = null;
    private Response response = null;
    FormBody.Builder formBodyBuilder = null;
    private RequestBody requestBody = null;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public BaseHttpClient(){
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10,TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(10,TimeUnit.SECONDS)//设置连接超时时间
                .build();
    }

    /**
     * HTTP Get请求方法
     *
     * @param url 请求URL
     * @return 返回结果
     * @exception IOException
     *
     * */
    public String get(String url) throws IOException {
        String result = "";
        request = new Request.Builder()
                .url(url)
                .build();

        response = okHttpClient.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * HTTP Post请求方法
     *
     * @param url 请求URL
     * @param params post参数表
     * @return 返回结果
     * @exception IOException
     *
     * */
    public String post(String url, SortedMap<String,String> params) throws IOException {
        String result = "";
        formBodyBuilder = new FormBody.Builder();
        for(Map.Entry<String,String> item : params.entrySet()){
            formBodyBuilder.add(item.getKey(),item.getValue());
        }

        requestBody = formBodyBuilder.build();
        request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        response = okHttpClient.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * HTTP Post请求方法
     *
     * @param url 请求URL
     * @param json JSON形式参数表
     * @return 返回结果
     * @exception IOException
     *
     * */
    public String post(String url,String json) throws IOException{
        String result = "";

        requestBody = RequestBody.create(JSON, json);
        request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        response = okHttpClient.newCall(request).execute();
        result = response.body().string();
        return result;
    }

    /**
     * 检测当前网络是否可用
     * @param context 组件上下文
     * @return 是否可用
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == connectivityManager) {
            //上下文为空返回false
            return false;
        } else {
            //获取全部网络信息
            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            if (null != networkInfos) {
                for (int i = 0; i < networkInfos.length; i++) {
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
