package com.yang.entity;

import java.io.Serializable;

/**
 * Created by yang on 2016/9/13 0013.
 */
public class Student implements Serializable{

    private String stuName;
    private Integer stuId;
    private Integer stuAge;

    public Student(String stuName, Integer stuId, Integer stuAge) {
        this.stuName = stuName;
        this.stuId = stuId;
        this.stuAge = stuAge;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", stuId=" + stuId +
                ", stuAge=" + stuAge +
                '}';
    }
}
