package com.yang.customview.mycustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yang.day01.R;

/** 在自定义View,在图片上面绘制文字
 * Created by yang on 2016/9/30 0030.
 */
public class DrawBitmapView extends View{

    Paint paint;

    String str = "好好学习";
    Rect rect = new Rect();
    Matrix matrix;  //矩阵
    float scale = 1;
    float rotate = 0;

    public void changeScale(float scale){ //改变尺寸
        this.scale = scale;
        //主线程调用onDraw
        invalidate();
    }

    public void changeRotate(float rotate){
        this.rotate = rotate;
        invalidate();
    }

    public DrawBitmapView(Context context) {
        this(context,null);
    }

    public DrawBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        matrix = new Matrix();

        paint.getTextBounds(str,0,str.length(),rect);
        paint.setTextSize(70);
        paint.setTextAlign(Paint.Align.CENTER);//设置文本水平居中

        paint.setColor(Color.BLUE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        matrix.reset(); //每次调用onDraw重置matrix
        matrix.setScale(scale,scale); //设置缩放
        matrix.setRotate(rotate);  //每次set一次，整个Matrix的数组都会变掉,因此只有一个效果

        canvas.drawColor(Color.GREEN); //设置画布颜色

        //bug2解决方案：创建bitmap对象时：拷贝了一个位图（这个拷贝的位图是可以编辑改变的）
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart_3).
                copy(Bitmap.Config.ARGB_8888,true);      //设为true表示可以编辑修改

        //canvas.setBitmap(bitmap); // bug1:系统提供的画布，不支持setBitmap()方法

        //bug1解决方案：自定义一个画布，设置bitmap
        Canvas canvas1 = new Canvas();
        canvas1.setBitmap(bitmap);//bug2:(IllegalStateException):bitmap默认不能改变

        //设置文本的位置：在图片中心位置，（数学计算）
        canvas1.drawText(str,bitmap.getWidth()/2,bitmap.getHeight()/2+rect.height()/2,paint);

        //bug3：绘制图形是：必须使用系统的canvas
        //canvas.drawBitmap(bitmap,200,300,paint);//参数二、三含义：在控件中的位置
        //设置bitmap的缩放
        canvas.drawBitmap(bitmap,matrix,paint);


    }

    //    public DrawBitmapView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


}
