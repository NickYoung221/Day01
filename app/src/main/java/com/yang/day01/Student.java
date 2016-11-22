package com.yang.day01;


import java.io.Serializable;

/**
 * Created by yang on 2016/9/10 0010.
 */
public class Student implements Serializable {
    Integer stuId;
    String stuName;

    public Student(){}

    public Student(Integer stuId, String stuName){
        super();
        this.stuId=stuId;
        this.stuName=stuName;
    }

    public String getStuName() {
        return stuName;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
