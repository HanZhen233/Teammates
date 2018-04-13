package com.example.teammates.search_part;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.teammates.R;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployerActivity extends AppCompatActivity {
    //
//    private Button search;
//    private String[] data;
//    private List<String> data1ist;
//    private List<String> data1ist_level;
//    private Spinner spinner;
//    private ArrayAdapter<String> adapter;
//    private ArrayAdapter<String> adapter_level;
//    private Spinner spinner_level;
//
//    private EditText search_content;
//    private android.support.v7.widget.Toolbar toolbar;
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_employer);
    }
}
//
//        search=(Button)findViewById(R.id.search_employer);
//        search_content=(EditText)findViewById(R.id.search_content);
//
//        toolbar=(android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SearchEmployerActivity.this,"正在搜索",Toast.LENGTH_LONG).show();
//                //按名字
//            }
//        });
//        spinner = (Spinner)findViewById(R.id.spinner);
//        spinner_level=(Spinner)findViewById(R.id.spinner_level);
//        // data = getResources().getStringArray(R.array.data);
//        data1ist = new ArrayList<>();
//        data1ist_level=new ArrayList<>();
//        initData();
//        //data可以修改为data1，数据可以是数组也可以是集合
//        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,data1ist);
//        adapter_level = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,data1ist_level);
//        spinner.setAdapter(adapter);
//        spinner_level.setAdapter(adapter_level);
//    }
//
//    public void initData(){
//
//        data1ist.add("武汉理工大学");
//        data1ist.add("华中师范大学");
//        data1ist.add("华中科技大学");
//        data1ist.add("武汉大学");
//
//        data1ist_level.add("国家级");
//        data1ist_level.add("省级");
//        data1ist_level.add("市级");
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        //搜索 所有队伍  找到 武汉理工大学所属
//        String university_name="";
//        university_name=data1ist.get(pos);
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//}
