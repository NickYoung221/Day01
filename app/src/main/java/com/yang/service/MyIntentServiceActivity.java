package com.yang.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 测试IntentService
 */
public class MyIntentServiceActivity extends AppCompatActivity {

    @InjectView(R.id.btn_startIntentService)
    Button btnStartIntentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intent_service);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_startIntentService)
    public void onClick() {
        //启动IntentService
        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("name","我的IntentService");
        startService(intent);
    }
}
