package com.example.teammates.search_part;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.teammates.R;

public class SearchEmployeeActivity extends AppCompatActivity {

    private Button search;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);
        search=(Button)findViewById(R.id.search_employee);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchEmployeeActivity.this,"正在搜索",Toast.LENGTH_LONG).show();
            }
        });
    }
}
