package com.yang.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yang.customview.mycustomview.DrawBitmapView;
import com.yang.day01.R;

public class DrawBitmapActivity extends AppCompatActivity {

    DrawBitmapView drawBitmapView;
    Button btn_changeScale;
    Button btn_changeRotate;
    float scale = 1;
    float rotate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap);

        drawBitmapView = (DrawBitmapView) findViewById(R.id.drawBitmapView);
        btn_changeScale = (Button) findViewById(R.id.btn_changeScale);
        btn_changeRotate = (Button) findViewById(R.id.btn_changeRotate);

        btn_changeScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮，改变DrawBitmapView的缩放值
//                if(scale>0.2){
//                    scale=scale-0.1f; //尺寸减0.1
//                    drawBitmapView.changeScale(scale);
//                }
                if(scale<2){
                    scale=scale+0.2f;  //尺寸加一
                    drawBitmapView.changeScale(scale);
                }
            }
        });

        btn_changeRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮，旋转DrawBitmapView
                rotate=rotate+20;
                drawBitmapView.changeRotate(rotate);
            }
        });


    }



}
