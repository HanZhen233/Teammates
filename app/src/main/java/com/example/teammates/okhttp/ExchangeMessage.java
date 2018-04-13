package com.example.teammates.okhttp;

import android.util.Log;

import com.example.teammates.db.user.User;
import com.example.teammates.RegisterActivity;

import com.google.gson.Gson;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by linji on 2018/4/12.
 */

public class ExchangeMessage {

    public static void sendRegister(User user){//由 参数param决定是 发出 User


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    User user = DataSupport.findFirst(User.class);//获得本地数据库中的用户名
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("username",user.getName())
                            .add("password",user.getPassword())
                            .add("sex",user.getSex())
                            .add("university",user.getUniversity())
                            .add("major",user.getMajor())
                            .add("technology",user.getTechnology())
                            .add("phone",user.getPhone())
                            .add("qq",user.getQq())
                            .add("mail",user.getEmail())
                            .build();

                    Request request=new Request.Builder()
                            .url("http://106.14.199.25:9091/addUser")
                            .post(requestBody)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    if(responseData.equals("添加成功")){
                        Log.d("register", "注册成功");
                    }
                 //   parseJSONWithGSON(responseData,"user");//用gson处理    注册的时候用不到
                    //showResponse(content);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    public static void getTeam_uName(final String university_name){//由参数决定是 competition还是，user
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                try {
//                    RequestBody requestBody=new FormBody.Builder()
//                            .add("university",university_name)
//                            .build();
//                    Request request=new Request.Builder()
//                            .url("106.14.199.25:9091/teamInfo/browseTeamInfo/byUniversity")//
//                            .post(requestBody)
//                            .build();
//                    Response response=client.newCall(request).execute();//登陆的时候 不会一起返回消息 返回 用户的person信息
//                    parseJSONWithGSON(response.body().toString(),"university");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//
//    }

    public static void sendrequestWithOkHttp(final String account,final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder()
                            .add("username",account)
                            .add("password",password)
                            .build();
                    Request request=new Request.Builder()
                            .url("http://106.14.199.25:9091/login")
                            .post(requestBody)
                            .build();
                  //  Response response=client.newCall(request).execute();
                    //String responseData=response.body().string();

                    //String content=parseJSONWithGSON(responseData);
                    //showResponse(content);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static void sendLogin(final String account, final String password){//由参数决定是 competition还是，user
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();

                RequestBody requestBody=new FormBody.Builder()
                        .add("username",account)
                        .add("password",password)
                        .build();
                Request request=new Request.Builder()
                        .url("http://106.14.199.25:9091/login")//
                        .post(requestBody)
                        .build();
                //Response response=client.newCall(request).execute();//登陆的时候 不会一起返回消息 返回 用户的person信息
                //parseJSONWithGSON(response.body().toString(),"user");


            }
        }).start();

    }
    public static void getLogin(){//由参数决定是 competition还是，user
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                try {

                    Request request=new Request.Builder()
                            .url("106.14.199.25:9091/logined")//
                            .build();
                    Response response=client.newCall(request).execute();//登陆的时候 不会一起返回消息 返回 用户的person信息
                    parseJSONWithGSON(response.body().toString(),"user");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
//    public static void getCompetition(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                try {
//
//                    Request request=new Request.Builder()
//                            .url("106.14.199.25:9091/competition/simpleContentAll")//
//                            .build();
//                    Response response=client.newCall(request).execute();
//                    parseJSONWithGSON(response.body().toString(),"competition");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//    }


    public static List<String> competition_names=new ArrayList<>();

    public static void  parseJSONWithGSON(String jsonDate,String param){//用参数判断是哪个情况

        Gson gson=new Gson();
        String result="";
        switch (param){
            case "competition":
//                List<Competition> competitions=gson.fromJson(jsonDate,new TypeToken<Competition>(){}.getType());
//                List <String>competition_list=new ArrayList<>();
//                for(Competition c:competitions){
//                    competition_list.add(c.getCompetitionName());
//                }
//                competition_names=competition_list;
                break;
            case "user":
                User user =gson.fromJson(jsonDate,User.class);
                new RegisterActivity().savePerson(user);//调用在 RegisterActivity里的保存 Person在数据库中的代码

                break;
            case "university":

                break;

        }

    }

}
