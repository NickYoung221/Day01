package com.yang.day01;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yang.application.MyApplication;

/**
 * 实现步骤：
 * 1、找到控件：etname,etpwd
 * 2、设置按钮点击事件：
 *  获取输入内容，判断，处理
 */
public class MainActivity extends AppCompatActivity {

    TextView tvReceive;
    Button btn_clickToChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取Application对象
        MyApplication myApplication = (MyApplication) getApplication();
        //用get（）方法获取值
        String userName = myApplication.getUserName();

        Button btReturn = (Button)findViewById(R.id.btn_return);
        //设置按钮点击事件（监听器）
        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btnTo = (Button) findViewById(R.id.btn_sendToMyActivity);
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*  Intent intent=new Intent(JumpToActivity.this,JumpFromActivity.class);
                startActivity(intent);//没有返回，再次启动activity，得不到值*/
                //设置返回值：message
                EditText et_sendToMyActivity = (EditText)findViewById(R.id.et_sentdToMyActivity);
                String message = et_sendToMyActivity.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("message",message);
                setResult(RESULT_OK,intent);
                finish();//销毁当前activity (与返回键效果一样)
            }
        });

        btn_clickToChange = (Button) findViewById(R.id.btn_clickToChange);
        //点击字体颜色变红
//        btn_clickToChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Button btn = (Button) v;
//                btn.setTextColor(Color.RED);
//            }
//        });


        tvReceive = (TextView) findViewById(R.id.tv_2);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String receiveString = bundle.getString("user");
        tvReceive.setText(userName);


    }
}
