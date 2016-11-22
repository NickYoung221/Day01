package com.yang.customview;

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
 * 自定义View和属性
 */
public class MyCustomViewActivity extends AppCompatActivity {

    Button btn_toMyTextView;
    @InjectView(R.id.btn_toDrawBitmapView)
    Button btnToDrawBitmapView;
    @InjectView(R.id.btn_toWidgetTitleView)
    Button btnToWidgetTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_custom_view);
        ButterKnife.inject(this);

        btn_toMyTextView = (Button) findViewById(R.id.btn_toMyTextView);

        btn_toMyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCustomViewActivity.this, MyTextViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.btn_toDrawBitmapView, R.id.btn_toWidgetTitleView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toDrawBitmapView:
                Intent intent = new Intent(this,DrawBitmapActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_toWidgetTitleView:
                Intent intent2 = new Intent(this,MyWidgetTitleViewActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
