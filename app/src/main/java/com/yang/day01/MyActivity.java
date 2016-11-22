package com.yang.day01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 用户名：admin   密码：123
 * 隐式跳转按钮和登录按钮均会到MainActivity
 * <p>
 * 读取tv值  ：tv对象.getText()
 * 1)根据id找到控件==》控件对象
 * 2)tv对象.getText()
 * 设置tv值: setText()
 * 1)根据id找到控件==》控件对象
 * 2)tv.setText()
 * <p>
 * 点击按钮（点击事件），改变tv值
 * （1）xml文件中设置onClick属性：点击按钮时候，调用的方法
 * (2) 定义方法：参数：View v ，返回：void
 * （3）方法体：改变tv值
 * Created by yang on 2016/9/8 0008.
 */
public class MyActivity extends AppCompatActivity {

    public static final int MYREQUESTCODE = 1;
    Button hideJumpView;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        login = (Button) findViewById(R.id.login);
        //如果activity已经开启，并且没有做处理，那么intent不为空(即不管有没有intent传来，getIntent()均不为空)
        // 这里要判断bundle是否为空，否则直接打开该页面会出错，因为getString()方法用在null上
        Intent intent = getIntent();
        //String value = intent.getStringExtra("value");  //也可以通过Bundle获得值，方法如下
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            //获得SendToActivityFragment传来的值，并展现在登录按钮上
            String value = bundle.getString("value");
            login.setText(value);
        }
        //隐式跳转，不指定具体的Activity，只设置目标Activity的条件
        // 若多个activity均满足条件，则会让用户自己选择
        hideJumpView = (Button) findViewById(R.id.btn_hideJumpFrom);
        hideJumpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置条件，这里的条件是目标activity的属性，可以在AndroidManifest.xml里面配置
                //设置想要跳转的目标的action属性，这里想要跳转到HiddenActivity，
                Intent intent = new Intent("com.yang.action");
                //设置数据和类型
                String str = "http://com.yang.host:8080/";
                //String转换为Uri
                intent.setDataAndType(Uri.parse(str), "yang");
                //默认有category属性："android.intent.category.DEFAULT",需要在配置文件里设置

                //利用intent传对象和集合到HideActivity中
                Student stu1 = new Student(19, "张三");
                Student stu2 = new Student(20, "李四");

                List<Student> students = new ArrayList<Student>();
                students.add(stu1);
                students.add(stu2);
                //利用intent传对象，被传递的对象要继承Serializable
                Bundle bundle = new Bundle();
                //这里要放到Bundle里面，因为只有Bundle有putSerializable方法，intent没有这个方法
                bundle.putSerializable("oneStudent", stu1);
                //intent.putExtra("oneStudent",stu1);
                //传对象的集合，使用.toArray()方法，接收数据的时候要放到数组里面
                //intent.putExtra("allStudent",students.toArray());
                //或者下面这种方法：接收的时候放到集合里面
                intent.putExtra("allStudent", (Serializable) students);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

    }

    public void login(View v) {
        EditText userView = (EditText) findViewById(R.id.user);
        EditText passwordView = (EditText) findViewById(R.id.password);
        TextView loginStateView = (TextView) findViewById(R.id.loginState);
        String user = userView.getText().toString();
        String password = passwordView.getText().toString();
        if ("admin".equals(user) && "123".equals(password)) {
            Toast.makeText(MyActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            loginStateView.setText("登录成功~~");
            Intent intent = new Intent(); //跳转到另一activity之中 ，（intent:意图，目的）
            intent.setClass(MyActivity.this, MainActivity.class);
            //第二种传值方式：不用创建Bundle，内部会创建
            intent.putExtra("user", user);

            /*第一种传值方式：
            Bundle bundle = new Bundle();
            bundle.putString("user",user);
            //还可以传第二个值
            bundle.putString("password",password);
            intent.putExtras(bundle);*/

            //MyActivity.this.startActivity(intent);//执行跳转,
            //若想达到跳转后返回的效果，并且得到跳转的页面得到的值，则不能用上面的方法,用下面的
            startActivityForResult(intent, MYREQUESTCODE);


            Log.d("Login", user);  //Log写入debug层，可以在日志文件中搜索关键字“Login”查看到

        } else {
            loginStateView.setText("登录失败!即将退出");
            Toast.makeText(MyActivity.this, "登录失败！！", Toast.LENGTH_SHORT).show();

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(3000); //这里要开一个子线程执行操作，最好不要在主线程里执行sleep
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }.start();

        }


    }

    public void takePhotos(View v) {   //打开相机拍照
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//这个命令就是调到拍照界面
        startActivityForResult(intent, MYREQUESTCODE);
    }

    /*
    得到跳转的界面获得的值，需要使用startActivityForResult，并且这里要重写onActivityResult方法
    请求码：标识哪个请求
    结果码：响应成功，RESULT_OK
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //响应成功
        if (resultCode == RESULT_OK && requestCode == MYREQUESTCODE) {
            //获取MainActivity用户输入的内容，显示在tv_returnResult上
            TextView tv_returnResult = (TextView) findViewById(R.id.tv_returnResult);//找到控件
            String returnResult = data.getStringExtra("message");//获得bundle里的值
            tv_returnResult.setText(returnResult);

            //获取拍照的图片，显示到iv_photo上
            ImageView iv_photo = (ImageView) findViewById(R.id.iv_photo);
            Bitmap b = data.getParcelableExtra("data");  //Bitmap是缩略图，因此显示出来的是略缩图
            iv_photo.setImageBitmap(b);

        }

    }


}