Android App
关于一些类的大致功能

在Compete包中，是关于 比赛的类，

Data包中是各种数据类，表，，Competition是打算新建的比赛的表
Message是 消息表
Person 是用户基本信息 的表
person_extend 用户拓展信息
Projection 是关于发的项目的表


在DataActivities包中

DataSelfActivity  是关于 我的 界面的相应活动
MessageActivity  关于消息的界面相应活动


在fragment包中 就是那四个碎片

在recycler_item包中关于消息的 界面的recyclerview

在search_part包中，是关于 找队伍 和  招队友  的类

Register的时候呢，在RegisterActivity中调用 ExchangeMessage.sendRegister(user)

将刚才填写的user送去后台。

login的时候,在LoginActivity中调用ExchangeMessage.sendrequestWithOkhttp()

里面的parseJsonWithGson()方法是 传入String jsdata,String param  param表示返回的信息是哪个类，虽然没有用到
   

---
1. adapter包(包含所有的适配器)
   - CommentAdapter 队伍下方的评论的适配器。
   - CompetitionAdapter 比赛项目的适配器，用于app首页的展示。
   - TeamInfoAdapter 队伍的适配器。
2. competition包（与比赛有关的活动,括号里时是对应的布局文件)
   - AddTeamActivity（addfindinfo_activity.xml） 发起新队伍时的活动
   - CompetitionDetailActivity(activity_compete_detail.xml) 点击首页的任意一个比赛项目进入的活动，里面有
      - 此比赛项目的详细信息(compete_item.xml)
      - 此比赛项目对应的所有队伍(team_info.xml)
   - CompetitionSearchActivity (activity_home_search.xml) 点击首页上方的搜索图样进入的比赛项目搜索活动。
3. DataActivity包
   - 林家宝的部分
4. db包
   - commentInfo
      - CommentInfo队伍下方的评论的数据类
   - competitionInfo
      - CompetitionSimpleInfo 比赛项目的简略版数据类，用于首页的比赛信息显示。
      - CompetitionWholeInfo 比赛项目的详细版数据类，用于点击首页的具体比赛项目后跳转到的比赛详情界面。
   - teamInfo
      - TeamInfo 队伍信息类。
   - user
      - User用户信息类。
5. fragment包（所有的碎片）
   - HomeFragment 主页
   - MeFragment 我的
   - MessageFragment 个人信息
   - SearchFragment 搜索
6. okhttp包
   - 林家宝的部分
7. search_part包
   - 林家宝的部分
8. util包(用于网络请求）
   - BaseHttpClient 网络请求类
   - CompetitionInfoThread获取比赛信息类
   - TheamInfoThread获取队伍信息类
 
