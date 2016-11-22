package com.yang.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Tween（补间动画），可设置透明度，缩放，旋转，平移，也可以组合使用
 */
public class TweenActivity extends AppCompatActivity {

    @InjectView(R.id.btn_alpha)
    Button btnAlpha;
    @InjectView(R.id.btn_scale)
    Button btnScale;
    @InjectView(R.id.btn_rotate)
    Button btnRotate;
    @InjectView(R.id.btn_translate)
    Button btnTranslate;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.btn_combination)
    Button btnCombination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.btn_alpha, R.id.btn_scale, R.id.btn_rotate, R.id.btn_translate, R.id.btn_combination})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alpha://设置透明度
                //设置透明度由全透明变为全不透明
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(2000); //设置时间
                //alphaAnimation.setFillAfter(true);//固定动画在结束位置
                alphaAnimation.setInterpolator(new AccelerateInterpolator()); //加速动画

                imageView.startAnimation(alphaAnimation);
                break;
            case R.id.btn_scale://设置尺度变化（缩放）
                //从0到原来2倍，中心点在图片中心
//                ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,2,Animation.RELATIVE_TO_SELF,
//                        0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//                scaleAnimation.setDuration(2000);
//                scaleAnimation.setFillAfter(true);

                //也可以自定义一个动画文件anim，然后直接读取xml文件
                ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.myscale);
                scaleAnimation.setFillAfter(true);
                scaleAnimation.setDuration(5000);  //如果在xml文件里面已经设置了时间，以这里的时间为准
                imageView.startAnimation(scaleAnimation);
                break;
            case R.id.btn_rotate://设置旋转
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                        0f, Animation.RELATIVE_TO_SELF, 0f);//设为0.5f表示以自身中心为旋转中心，0f表示左上角，类推
                rotateAnimation.setDuration(2000);//设置时间
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new AccelerateInterpolator());//加速动画
                rotateAnimation.setRepeatCount(2);//设置重复次数，3次

                imageView.startAnimation(rotateAnimation);
                break;
            case R.id.btn_translate://设置位置变化（平移）
                //平移:初始位置（原来位置+fromX,原来位置+fromY）  结束位置（原来位置+toX,原来位置+toY）
                // TranslateAnimation translateAnimation=new TranslateAnimation(100,200,100,200);//(0,0)->(2,2)

                //初始位置：（原来位置-图片一半大小，原来位置+图片一半大小）
                //注意每个位置的参数含义，与上面不太一样
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -0.5f,
                        Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
                //向左平移一个单位
//                TranslateAnimation translateAnimation=new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,
//                        Animation.RELATIVE_TO_SELF,-1,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0);
                translateAnimation.setDuration(3000);
                translateAnimation.setFillAfter(true);

                imageView.startAnimation(translateAnimation);

                //可以设置监听事件
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i("TweenActivity", "onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.i("TweenActivity", "onAnimationEnd: ");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.i("TweenActivity", "onAnimationRepeat: ");
                    }
                });
                break;
            case R.id.btn_combination://组合动画
                //设为true表示：interpolator属性：父控件设置的为准，false(各自用自己的)
                AnimationSet animationSet = new AnimationSet(true);

                AlphaAnimation alphaAnimation1 = new AlphaAnimation(0, 1);
                alphaAnimation1.setDuration(2000);
                animationSet.addAnimation(alphaAnimation1);//透明度变化

                ScaleAnimation scaleAnimation1 = new ScaleAnimation(0,2,0,2,Animation.RELATIVE_TO_SELF,
                        0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation1.setDuration(2000);
                scaleAnimation1.setFillAfter(true);
                animationSet.addAnimation(scaleAnimation1);//尺度变化

                RotateAnimation rotateAnimation1 = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
                        0f, Animation.RELATIVE_TO_SELF, 0f);
                rotateAnimation1.setDuration(2000);
                rotateAnimation1.setFillAfter(true);
                animationSet.addAnimation(rotateAnimation1);//设置旋转变化

                animationSet.setInterpolator(new DecelerateInterpolator());//设置减速动画
                imageView.startAnimation(animationSet);
                break;
        }
    }



}
