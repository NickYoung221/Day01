package com.yang.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 点击按钮改变箭头方向
 */
public class ChangeArrowDirectionActivity extends AppCompatActivity {

    RotateAnimation rotateAnimation;

    @InjectView(R.id.btn_changeArrowDirection1)
    Button btnChangeArrowDirection1;
    @InjectView(R.id.btn_changeArrowDirection2)
    Button btnChangeArrowDirection2;
    @InjectView(R.id.iv_arrow)
    ImageView ivArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_arrow_direction);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_changeArrowDirection1, R.id.btn_changeArrowDirection2})
    public void onClick(View view) {//后面角度减去前面角度，若为正：顺时针，若为负：逆时针
        switch (view.getId()) {
            case R.id.btn_changeArrowDirection1://点击改变箭头方向
                rotateAnimation = new RotateAnimation(0,-180, Animation.RELATIVE_TO_SELF,
                        0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000);//设置时间
                rotateAnimation.setFillAfter(true); //固定在动画结束位置

                ivArrow.startAnimation(rotateAnimation);

                break;
            case R.id.btn_changeArrowDirection2:
                rotateAnimation = new RotateAnimation(180,0, Animation.RELATIVE_TO_SELF,
                        0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotateAnimation.setDuration(1000);//设置时间
                rotateAnimation.setFillAfter(true); //固定在动画结束位置

                ivArrow.startAnimation(rotateAnimation);
                break;
        }
    }
}
