package com.yang.day01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yang.animation.AnimationActivity;
import com.yang.broadcast.BroadcastActivity;
import com.yang.cache.LoadImageActivity;
import com.yang.contentProvider.ContactInfoActivity;
import com.yang.customview.MyCustomViewActivity;
import com.yang.databases.SQLiteActivity;
import com.yang.datastorage.SharedPreferencesActivity;
import com.yang.net.AsyncTaskActivity;
import com.yang.net.MyVolleyActivity;
import com.yang.net.MyXUtilsActivity;
import com.yang.service.ServiceActivity;

public class Buttons2Activity extends AppCompatActivity implements View.OnClickListener{
    Button btn_toJson;
    Button btn_ToNet;
    Button btn_ToMyVolley;
    Button btn_ToMyXUtils;
    Button btn_ToAsyncTask;
    Button btn_toSharedPreferences;
    Button btn_toSQLite;
    Button btn_toBroadcastReceiver;
    Button btn_toLoadImage;
    Button btn_toService;
    Button btn_toContactInfo;
    Button btn_toAnimation;
    Button btn_toCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons2);

        btn_toJson = (Button) findViewById(R.id.btn_toJson);
        btn_ToNet = (Button) findViewById(R.id.btn_ToNet);
        btn_ToMyVolley = (Button) findViewById(R.id.btn_ToMyVolley);
        btn_ToMyXUtils = (Button) findViewById(R.id.btn_ToMyXUtils);
        btn_ToAsyncTask = (Button) findViewById(R.id.btn_ToAsyncTask);
        btn_toSharedPreferences = (Button) findViewById(R.id.btn_toSharedPreferences);
        btn_toSQLite = (Button) findViewById(R.id.btn_toSQLite);
        btn_toBroadcastReceiver = (Button) findViewById(R.id.btn_toBroadcastReceiver);
        btn_toLoadImage = (Button) findViewById(R.id.btn_toLoadImage);
        btn_toService = (Button) findViewById(R.id.btn_toService);
        btn_toContactInfo = (Button) findViewById(R.id.btn_toContactInfo);
        btn_toAnimation = (Button) findViewById(R.id.btn_toAnimation);
        btn_toCustomView = (Button) findViewById(R.id.btn_toCustomView);

        btn_toJson.setOnClickListener(this);
        btn_ToNet.setOnClickListener(this);
        btn_ToMyVolley.setOnClickListener(this);
        btn_ToMyXUtils.setOnClickListener(this);
        btn_ToAsyncTask.setOnClickListener(this);
        btn_toSharedPreferences.setOnClickListener(this);
        btn_toSQLite.setOnClickListener(this);
        btn_toBroadcastReceiver.setOnClickListener(this);
        btn_toLoadImage.setOnClickListener(this);
        btn_toService.setOnClickListener(this);
        btn_toContactInfo.setOnClickListener(this);
        btn_toAnimation.setOnClickListener(this);
        btn_toCustomView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_toJson:
                Intent intent = new Intent(this,JsonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ToNet:
                Intent intent2 = new Intent(this,NetActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_ToMyVolley:
                Intent intent3 = new Intent(this, MyVolleyActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_ToAsyncTask:
                Intent intent5 = new Intent(this, AsyncTaskActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_ToMyXUtils:
                Intent intent4 = new Intent(this, MyXUtilsActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_toSharedPreferences:
                Intent intent6 = new Intent(this, SharedPreferencesActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_toSQLite:
                Intent intent7 = new Intent(this, SQLiteActivity.class);
                startActivity(intent7);
                break;
            case R.id.btn_toBroadcastReceiver:
                Intent intent8 = new Intent(this, BroadcastActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_toLoadImage: //点击跳转到测试三级缓存机制的页面
                Intent intent9 = new Intent(this, LoadImageActivity.class);
                startActivity(intent9);
                break;
            case R.id.btn_toService: //点击跳转到测试Service的页面
                Intent intent10 = new Intent(this, ServiceActivity.class);
                startActivity(intent10);
                break;
            case R.id.btn_toContactInfo://点击跳转到测试ContentProvider的页面
                Intent intent11 = new Intent(this, ContactInfoActivity.class);
                startActivity(intent11);
                break;
            case R.id.btn_toAnimation://点击跳转到测试动画的页面
                Intent intent12 = new Intent(this, AnimationActivity.class);
                startActivity(intent12);
                break;
            case R.id.btn_toCustomView:
                Intent intent13 = new Intent(this,MyCustomViewActivity.class);
                startActivity(intent13);
                break;
        }
    }




}
