package com.example.teammates.Data;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teammates.R;

public class MessageActivity extends AppCompatActivity {

    private ImageView titleImage;
    private TextView textContent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        titleImage=(ImageView)findViewById(R.id.title_image);
        textContent=(TextView)findViewById(R.id.information_in_message);
        int imageId=getIntent().getIntExtra("head",0);
        String content=getIntent().getStringExtra("content");

        textContent.setText(content);
        Glide.with(this).load(imageId).into(titleImage);

    }
}
