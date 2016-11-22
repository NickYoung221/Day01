package com.yang.animation;

import android.util.Log;
import android.view.View;

/** 测试ObjectAnimation所用到的java类
 * Created by yang on 2016/9/29 0029.
 */
public class TestObject {
    private int age;
    private View v;

    public TestObject(int age, View v) {
        this.age = age;
        this.v = v;
    }

    public int getAge() {
        Log.i("TestObject", "getAge: "+age);
        return age;
    }

    public void setAge(int age) {
        Log.i("TestObject", "setAge: "+age);
        this.age = age;
        //获取View的布局宽度,并将age赋给它
        v.getLayoutParams().width=age;
        v.requestLayout();  //重新调用onLayout方法，再次布局
    }

}
