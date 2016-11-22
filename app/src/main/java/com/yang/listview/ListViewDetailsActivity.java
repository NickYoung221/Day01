package com.yang.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yang.day01.R;
import com.yang.entity.Student;

public class ListViewDetailsActivity extends AppCompatActivity {
    TextView tv_name_Info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_details);

        tv_name_Info = (TextView) findViewById(R.id.tv_name_Info);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");

        tv_name_Info.setText(student.getStuName());
    }
}
