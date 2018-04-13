package com.example.teammates.DataActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teammates.R;

import org.w3c.dom.Text;

public class MessageActivity extends AppCompatActivity {

    private TextView sender_name;
    private TextView textContent;
    private TextView answer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        sender_name=(TextView)findViewById(R.id.message_sender_name);
        textContent=(TextView)findViewById(R.id.information_in_message);
        answer=(TextView)findViewById(R.id.answer);
        String content=getIntent().getStringExtra("content");

        textContent.setText(content);


    }
}
