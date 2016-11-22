package com.yang.day01;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yang.application.MyApplication;
import com.yang.listview.ListViewDActivity;

/**
 * 主界面，账号：admin  密码:123  登录跳转到ListViewDActivity（学生信息列表）,
 * 点击"注册新用户"可跳转到MyActivity
 */
public class LoginActivity extends Activity {
    EditText userView;
    EditText passwordView;
    Button loginView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标头，但是要继承的是Activity
        setContentView(R.layout.activity_login);

        //获取Application对象， getApplication():  获取系统创建的Application对象,要在配置文件里进行配置
        MyApplication myApplication = (MyApplication) getApplication();
        //赋值
        myApplication.setUserName("杨过");

        userView = (EditText)findViewById(R.id.eT_name);
        passwordView = (EditText)findViewById(R.id.eT_pwd);
        loginView = (Button)findViewById(R.id.b_login);

        loginView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {  //注意：getText()要写在onClick方法里，否则得不到值
                String user = userView.getText().toString();
                String password = passwordView.getText().toString();
                if ("admin".equals(user) && "123".equals(password)){
                    Toast.makeText(LoginActivity.this, "登录成功~~~", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this,ListViewDActivity.class);
                    //第一种传值方式
                    Bundle bundle = new Bundle();
                    bundle.putString("user",password);
                    intent.putExtras(bundle);

                    LoginActivity.this.startActivity(intent);
                } else{
                    Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void toButtonsActivity(View v){     // 点击"找回密码"跳到ButtonsActivity
        Intent intent = new Intent(this,ButtonsActivity.class);
        startActivity(intent);
    }

    public void toMyActivity(View v){     //点击"注册新用户"跳到MyActivity
        Intent intent = new Intent(LoginActivity.this,MyActivity.class);
        startActivity(intent);
    }

    public void toViewPagerActivity(View v){    //点击用户头像会跳到向导页面
        Intent intent = new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }


}
