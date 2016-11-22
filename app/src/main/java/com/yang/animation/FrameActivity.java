package com.yang.animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.yang.day01.R;

/**
 * Frame（帧动画）
 */
public class FrameActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        iv = (ImageView) findViewById(R.id.iv_frame);

        //获取图片背景
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getBackground();
        //开始动画
        animationDrawable.start();

    }



}
