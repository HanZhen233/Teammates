package com.example.teammates.Compete;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.teammates.R;

public class CompeteDetailActivity extends AppCompatActivity {
    public static final String Compete_Name="compete_name";
    public static final String Compete_Image_Id="compete_image_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compete_detail);
        Intent intent=getIntent();
        String competeName=intent.getStringExtra(Compete_Name);
        int competeImageId=intent.getIntExtra(Compete_Image_Id,0);
        Toolbar toolbar=(Toolbar) findViewById(R.id.detail_toolbar);
        CollapsingToolbarLayout collapsingToolbar=(CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        ImageView competeImage=(ImageView) findViewById(R.id.compete_image_view);
        TextView competeContentText=(TextView) findViewById(R.id.compete_content_text);
        setSupportActionBar(toolbar);
        ActionBar actioinBar=getSupportActionBar();
        if(actioinBar!=null){
            actioinBar.setDisplayHomeAsUpEnabled(true);
            collapsingToolbar.setTitle(competeName);
        }
        Glide.with(this).load(competeImageId).into(competeImage);
        String competeContent=generateCompeteContent(competeName);
        competeContentText.setText(competeContent);
    }

    private String generateCompeteContent(String competeName){
        return competeName+"的介绍";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
