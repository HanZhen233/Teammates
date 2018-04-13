/*比赛项目的搜索*/
package com.example.teammates.competition;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;
import com.example.teammates.R;

public class CompetitionSearchActivity extends AppCompatActivity {
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        绑定视图
        setContentView(R.layout.activity_home_search);
//        绑定组件
        searchView=(SearchView) findViewById(R.id.carson_search_view);
//        searchView
//        设置点击键盘上的搜索按键后的操作（通过回调接口）
//        参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });

        // 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }
}
