package com.yang.customview.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by yang on 2016/9/29 0029.
 */
public class MyTextView extends TextView {

    String str = "好好学习，不打游戏";
    Rect rect = new Rect();

    Paint paint;

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.RED); //设置画笔颜色
        paint.setTextSize(50); //设置字体大小
        paint.setTextAlign(Paint.Align.CENTER);
    }

//    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN); //设置画布颜色

        Log.i("MyTextView", "onDraw: getMeasuredWidth()"+getMeasuredWidth()+",getMeasuredHeight():"+
        getMeasuredHeight()+"getWidth()"+getWidth());
        //绘制文本(设置让文本居中)
        canvas.drawText(str,getWidth()/2,getHeight()/2+rect.height()/2,paint);
    }

    /**
     * 测量控件的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //控件大小和布局模式,位运算
        //取出父控件给出的宽度
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        Log.i("MyTextView", "onMeasure:parentWidth "+parentWidth+",widthMode:"+widthMode);

        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        Log.i("MyTextView", "onMeasure:parentHeight "+parentHeight+",heightMode:"+heightMode);

        //自己设置的高度和宽度
        int myWidth = 0;
        int myHeight = 0;

        paint.getTextBounds(str,0,str.length(),rect);//参数含义？

        if(widthMode==MeasureSpec.AT_MOST){  //宽度模式是wrap_content
            //文本的大小+padding的距离+50
            int textWidth = rect.width(); //获取文本宽度
            //设置myWidth
            myWidth = textWidth+getPaddingLeft()+getPaddingRight()+50;
            //Log.i("MyTextView", "onMeasure: wrap_content");

        }
//        else if(widthMode==MeasureSpec.EXACTLY){ //宽度模式是match_parent或者具体的值
//            Log.i("MyTextView", "onMeasure: match_parent或者具体的值");
//        }

        if(heightMode==MeasureSpec.AT_MOST){
            int textHeight = rect.height();  //文本的高度
            myHeight = textHeight+getPaddingTop()+getPaddingBottom()+50;
        }
        Log.i("MyTextView", "onMeasure: myWidth"+myWidth+",myHeight"+myHeight);
        //设置view的宽度和高度为自己定义的尺寸
        setMeasuredDimension(myWidth,myHeight);

    }





}
