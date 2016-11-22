package com.yang.animation;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yang.day01.R;

/**
 * 属性动画
 */
public class ObjectAnimationActivity extends AppCompatActivity {

    Button btn_ObjectAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);

        btn_ObjectAnimation = (Button) findViewById(R.id.btn_ObjectAnimation);
        btn_ObjectAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TestObject的age属性，从一个值变成另一个值
                TestObject testObject = new TestObject(20,v);

                //若参数是三个，最后一个表示的是结束的值，age属性要初始化
                //ObjectAnimator objectAnimator = ObjectAnimator.ofInt(testObject,"age",100);
                //若参数是四个，第三个参数表示开始值，第四个参数表示结束时的值，属性可以不初始化
                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(testObject,"age",100,500);

                objectAnimator.setDuration(3000);
                objectAnimator.start();

            }
        });
    }





}
