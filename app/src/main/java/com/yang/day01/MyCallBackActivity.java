package com.yang.day01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yang.callback.*;
import com.yang.callback.MyActivity;

/**
 * 测试回调机制
 */
public class MyCallBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_call_back);

        MyButton myButton = new MyButton();
        myButton.setOnClickListener(new MyActivity());
    }
}
