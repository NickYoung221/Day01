package com.yang.customview.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yang.day01.R;

/** 自定义容器,解析的布局文件是my_widget_title_bar，（可以多次复用）
 *  在属性里定义一些属性declare-styleable，
 *  当使用自定义的容器时，就可以布局文件中使用自定义的属性，
 * Created by yang on 2016/10/1 0001.
 */
public class MyWidgetTitleView extends RelativeLayout{

    public MyWidgetTitleView(Context context) {
        this(context,null);
    }

    public MyWidgetTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //解析xml文件，读取属性，给不同控件
        LayoutInflater inflater= LayoutInflater.from(context);
        //获取控件
        View v=inflater.inflate(R.layout.my_widget_title_bar,this);//将解析的view，加到当前的线性布局中

        ImageView leftImage= (ImageView) findViewById(R.id.left_image);
        ImageView rightImage= (ImageView) findViewById(R.id.right_image);//右边图片
        TextView tv= (TextView) findViewById(R.id.title);//标题
        RelativeLayout root= (RelativeLayout) findViewById(R.id.root);
        RelativeLayout right_layout = (RelativeLayout) findViewById(R.id.right_layout);

        //获取所有属性值
        TypedArray ta= getContext().obtainStyledAttributes(attrs, R.styleable.MyWidgetTitleView,0,0);//获取属性
        Drawable leftDraw= ta.getDrawable(R.styleable.MyWidgetTitleView_leftImage);
        Drawable rightDraw=ta.getDrawable(R.styleable.MyWidgetTitleView_rightImage);
        String text=ta.getString(R.styleable.MyWidgetTitleView_textStr);
        Drawable back=ta.getDrawable(R.styleable.MyWidgetTitleView_backImage);
        int color=ta.getColor(R.styleable.MyWidgetTitleView_textColor, Color.BLACK);

        Drawable rightBackground = ta.getDrawable(R.styleable.MyWidgetTitleView_rightImageBackground);

        ta.recycle();

        if(rightBackground!=null) {
            right_layout.setBackgroundDrawable(rightBackground);
        }

        if(leftDraw!=null){
            leftImage.setImageDrawable(leftDraw);
        }

        if(rightDraw!=null){
            rightImage.setImageDrawable(rightDraw);
        }

        if(text!=null){
            tv.setText(text);
        }

        if(back!=null){
            root.setBackgroundDrawable(back);
        }

        tv.setTextColor(color);
    }




}
