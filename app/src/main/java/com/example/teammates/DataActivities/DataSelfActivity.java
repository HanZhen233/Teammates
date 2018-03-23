package com.example.teammates.DataActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.teammates.Data.Person;
import com.example.teammates.R;
import com.example.teammates.RegisterActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class DataSelfActivity extends AppCompatActivity {

    private LinearLayout self_information;
    private LinearLayout setting;
    private LinearLayout aboutUs;
//
    private TextView name_information;
    private TextView university_information;
    private TextView school_information;
    private TextView technology_information;
    private TextView phone_information;
    private Button update;

    private Button cancellation_setting;//注销

    private TextView us_aboutUs;

    private void initView(){
        self_information=(LinearLayout)findViewById(R.id.self_information);
        setting=(LinearLayout)findViewById(R.id.setting);
        aboutUs=(LinearLayout)findViewById(R.id.about_us);

        name_information=(TextView)findViewById(R.id.name);
        university_information=(TextView)findViewById(R.id.university);
        school_information=(TextView)findViewById(R.id.school);
        technology_information=(TextView)findViewById(R.id.technology);
        phone_information=(TextView)findViewById(R.id.phone);
        update=(Button)findViewById(R.id.update);

        cancellation_setting=(Button)findViewById(R.id.cancellation);

        us_aboutUs=(TextView)findViewById(R.id.team_instruction);

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myself);
        initView();
        final Intent intent=getIntent();
        String content=intent.getStringExtra("key");

        switch (content){
            case "个人信息":
                self_information.setVisibility(View.VISIBLE);
                List<Person> persons= DataSupport.findAll(Person.class);
                if(persons.size()>0){
                    for(Person person:persons){
                        name_information.setText(person.getName());
                        university_information.setText(person.getUniversity());
                        school_information.setText(person.getSchool());
                        technology_information.setText(person.getTechnology());
                        phone_information.setText(person.getPhone());

                    }
                }
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1=new Intent(DataSelfActivity.this, RegisterActivity.class);
                        startActivity(intent1);
                    }
                });

                break;
            case "关于我们":
                aboutUs.setVisibility(View.VISIBLE);
                String info="本产品来自武汉理工大学计算机的几个有志青年，经过一个寒假加上几个星期苦苦磨练出来的。如果有什么侵犯用户权益的地方请及时联系我们。我们的联系方式是：1234567";
                us_aboutUs.setText(info);
                break;
            case "设置":
                setting.setVisibility(View.VISIBLE);
                cancellation_setting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //status.setText("未登录");  属于更改 其他活动中的ui元素
                        name_information.setText("");
                        university_information.setText("");
                        school_information.setText("");
                        technology_information.setText("");
                        phone_information.setText("");

                        finish();
                    }
                });
                break;

        }


    }

}
