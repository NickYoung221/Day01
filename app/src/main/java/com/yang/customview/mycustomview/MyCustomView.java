package com.yang.customview.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yang.day01.R;

import java.util.ArrayList;
import java.util.List;

/** 自定义View
 * Created by yang on 2016/9/29 0029.
 */
public class MyCustomView extends View{

    Paint paint;

    //画笔颜色的集合
    List<Integer> colors = new ArrayList<Integer>();

    int i = 0;//标记用户的点击次数

    public void setColorList(){
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
    }

    public MyCustomView(Context context) {
        //super(context);
        this(context,null);
        Log.i("MyCustomView", "MyCustomView: 1个参数");
    }

    public MyCustomView(Context context, AttributeSet attrs) {
        //super(context, attrs);
        this(context,attrs,0);
        Log.i("MyCustomView", "MyCustomView: 2个参数");
    }

    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        setColorList();//设置画笔颜色的数据源

        //读取xml文件中设置的属性
        /**
         * 第二个参数：attrs中查找R.styleable.MyCustomView的值
         * 查找属性优先级：xml/style>R.attr.defStyleAttr(自定义属性赋值，必须放在theme)
         * 查找第四个参数对应样式的前提条件：第3个参数为0；或者第三个不为0&第三个属性没有在theme中赋值
         */

        //TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomView,0,0);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyCustomView,0,R.style.mydefStyleRes);
        /**
         * 参数一：属性名  参数二：默认颜色(如果自定义的style没有设置属性)
         */
        int color = typedArray.getColor(R.styleable.MyCustomView_mycolor, Color.BLUE);

        typedArray.recycle(); //回收
        //设置画笔颜色
        paint.setColor(color);

        //设置自定义View的点击事件
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击一次修改一次画笔的颜色
//                paint.setColor(colors.get(i++ % 5));
//                invalidate();//在主线程中手动调用onDraw,重新绘制View

                new Thread(){ //开辟一个子线程，用于不断修改画笔颜色（即自定义View的颜色）
                    @Override
                    public void run() {
                        super.run();
                        while(true){
                            paint.setColor(colors.get(i++ % 5)); //修改画笔的颜色

                            postInvalidate(); //在子线程中手动调用onDraw，与主线程不同
                            try {
                                Thread.sleep(300);//线程休眠
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

            }
        });




    }

    //画出我们自定义的View
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("MyCustomView", "onDraw: ");
        canvas.drawCircle(200,200,150,paint);//画出一个圆
    }

    /*public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/  //最低API要求为21,想要使用须将最低版本设为21






}
