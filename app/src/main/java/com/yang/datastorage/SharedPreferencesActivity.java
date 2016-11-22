package com.yang.datastorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yang.day01.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 数据存储，SharedPreferences的使用
 */
public class SharedPreferencesActivity extends AppCompatActivity {

    @InjectView(R.id.btn_1)
    Button btn1;
    @InjectView(R.id.btn_2)
    Button btn2;
    @InjectView(R.id.btn_3)
    Button btn3;
    @InjectView(R.id.btn_4)
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        ButterKnife.inject(this);


    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:   //写入SharedPreferences:
                //第一种：获取sharepreference对象:activity （只有当前的acitivy可以访问）
                //.xml结尾,默认名称：MainActivity.xml
                //SharedPreferencesActivity sp1=getPreferences(Context.MODE_PRIVATE);

                //第二种（推荐用这种方式）：参数1：sp的名字；参数2：Mode Context (MODE_PRIVATE:只能该应用本身访问)
                SharedPreferences sharedPreferences = getSharedPreferences("mySP", Context.MODE_PRIVATE);

                //存数据
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName","管理员"); //若key相同，只会存一次
                //提交
                editor.commit();

                break;
            case R.id.btn_2:  //从SharedPreferences中取数据

                SharedPreferences sharedPreferences2 = getSharedPreferences("mySP",Context.MODE_PRIVATE);
                //参数一：key，参数二：默认值（即key不存在的值）
                String userName = sharedPreferences2.getString("userName","userName-null");
                String userPwd = sharedPreferences2.getString("userPwd","userPwd-null");
                Toast.makeText(SharedPreferencesActivity.this, userName+":"+userPwd, Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_3:  //向file文件里面写入数据
                try {
                    FileOutputStream fos = openFileOutput("myfile",Context.MODE_PRIVATE);
                    fos.write("哈哈哈".getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btn_4:  //从file文件里面读取数据
                try {
                    FileInputStream fis = openFileInput("myfile");
                    //转换流
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                    StringBuilder sb = new StringBuilder();
                    String str;//读取每行内容
                    while((str=br.readLine())!=null){
                        sb.append(str);
                    }
                    Toast.makeText(SharedPreferencesActivity.this, sb, Toast.LENGTH_SHORT).show();
                    br.close();
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

        }
    }




}
