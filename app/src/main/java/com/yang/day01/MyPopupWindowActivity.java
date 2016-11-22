package com.yang.day01;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyPopupWindowActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;

    TextView v;
    PopupWindow pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_popup_window);

        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);


        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);


        //可以解析一个布局文件，也可以自定义一些控件
        v = new TextView(this);
        v.setText("这是内容~");

        pw = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                View contentView = LayoutInflater.from(MyPopupWindowActivity.this).inflate(R.layout.loginview,null);
                //创建popupwindow对象，参数含义？？为什么不能直接设置参数为200,300？？
                PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //设置在外部触摸的时候可以消失掉
                popupWindow.setOutsideTouchable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());//据说新版本不设置该方法也可以？？
                //设置popupWindow显示位置
                popupWindow.showAsDropDown(v);
                break;
            case R.id.btn_2:
                setDismiss(pw);//等价于pw.setOutsideTouchable(true);
                pw.showAsDropDown(v);
                break;
            case R.id.btn_3:
                setDismiss(pw);
                pw.showAsDropDown(v,100,200);  //设置偏移量
                break;
            case R.id.btn_4:
                setDismiss(pw);    // 真正的根布局其实是DecorView
                pw.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER,100,200);
                break;

        }
    }
    //	@Override    ???什么方法
//	public boolean onTouchEvent(MotionEvent event) {
//		// TODO Auto-generated method stub
//		//popupwindow
//		if(pw!=null){
//		pw.dismiss();
//		}
//		return super.onTouchEvent(event);
//	}
    public void setDismiss(final PopupWindow pwZonghe){  //设置在外部触摸的时候可以消失掉
        pwZonghe.setBackgroundDrawable(new BitmapDrawable()); //以前是要调用该方法，现在可以不调用

        pwZonghe.setOutsideTouchable(true);
    }
}
