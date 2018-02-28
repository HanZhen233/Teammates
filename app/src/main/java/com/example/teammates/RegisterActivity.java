package com.example.teammates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.teammates.Data.Person;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText university;
    private EditText school;
    private EditText technology;
    private EditText phone;
    private EditText message;

    private Button send;
    private void initView(){
        name=(EditText)findViewById(R.id.name_content);
        university=(EditText)findViewById(R.id.university_content);
        school=(EditText)findViewById(R.id.school_content);
        technology=(EditText)findViewById(R.id.technology_content);
        phone=(EditText)findViewById(R.id.phone_content);
        message=(EditText)findViewById(R.id.message_content);
        send=(Button)findViewById(R.id.send);

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person=new Person();
                person.setName(name.getText().toString());
                person.setUniversity(university.getText().toString());
                person.setSchool(school.getText().toString());
                person.setTechnology(technology.getText().toString());
                person.setPhone(phone.getText().toString());
                person.setMessage(message.getText().toString());
                person.save();
                finish();

            }
        });
    }
}
