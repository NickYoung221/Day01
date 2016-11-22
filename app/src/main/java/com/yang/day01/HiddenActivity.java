package com.yang.day01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class HiddenActivity extends AppCompatActivity {

    TextView tv_stuId ;
    TextView tv_stuName;
    TextView tv_student1_stuId;
    TextView tv_student1_stuName;
    TextView tv_student2_stuId;
    TextView tv_student2_stuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);
        tv_stuId = (TextView) findViewById(R.id.tv_stuId);
        tv_stuName = (TextView) findViewById(R.id.tv_stuName);
        tv_student1_stuId = (TextView) findViewById(R.id.tv_student1_stuId);
        tv_student1_stuName = (TextView) findViewById(R.id.tv_student1_stuName);
        tv_student2_stuId = (TextView) findViewById(R.id.tv_student2_stuId);
        tv_student2_stuName = (TextView) findViewById(R.id.tv_student2_stuName);


        //获得MyActivity传来的intent里的对象，并展示到TextView
        Intent intent = getIntent();
        Student stu1 = (Student) intent.getSerializableExtra("oneStudent");
        Integer stuId = stu1.getStuId();
        String stuName = stu1.getStuName();
        tv_stuId.setText(stuId.toString());
        tv_stuName.setText(stuName);
        //获得MyActivity传来的intent里的对象集合，有两种方式，要根据传值的方式来定
        //Object[] students = (Object[]) intent.getSerializableExtra("allStudent");数组的方式
        List<Student> students = (List<Student>) intent.getSerializableExtra("allStudent");
        //获得第一学生的信息，并展示
        Student students_1 = students.get(0); //(Student)students[0];
        Integer students_1_Id = students_1.getStuId();
        String students_1_Name = students_1.getStuName();
        tv_student1_stuId.setText(students_1_Id.toString());
        tv_student1_stuName.setText(students_1_Name);
        //获得第二个学生的信息，并展示
        Student students_2 = students.get(1);//(Student) students[1];
        Integer students_2_Id = students_2.getStuId();
        String students_2_Name = students_2.getStuName();
        tv_student2_stuId.setText(students_2_Id.toString());
        tv_student2_stuName.setText(students_2_Name);

    }
}
