package com.yang.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 动画效果：Frame、Tween、属性动画
 */
public class AnimationActivity extends AppCompatActivity {

    @InjectView(R.id.btn_toFrame)
    Button btnToFrame;
    @InjectView(R.id.btn_toTween)
    Button btnToTween;
    @InjectView(R.id.btn_toObjectAnimation)
    Button btnToObjectAnimation;
    @InjectView(R.id.btn_toChangeArrowDirection)
    Button btnToChangeArrowDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_toFrame, R.id.btn_toTween, R.id.btn_toObjectAnimation, R.id.btn_toChangeArrowDirection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toFrame://跳到帧动画Frame
                Intent intent = new Intent(this, FrameActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_toTween://跳到补间动画Tween
                Intent intent2 = new Intent(this, TweenActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_toObjectAnimation://跳转到属性动画
                Intent intent3 = new Intent(this, ObjectAnimationActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_toChangeArrowDirection:
                Intent intent4 = new Intent(this, ChangeArrowDirectionActivity.class);
                startActivity(intent4);
                break;
        }
    }


}
