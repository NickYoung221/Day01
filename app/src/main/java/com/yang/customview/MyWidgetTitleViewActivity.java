package com.yang.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yang.day01.R;

/**
 * 使用自定义的容器
 * 在xml文件里设置属性
 */
public class MyWidgetTitleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_widget_title_view);
    }

}
