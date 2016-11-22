package com.yang.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/** IntentService
 * Created by yang on 2016/9/27 0027.
 */
public class MyIntentService extends IntentService{

    public MyIntentService(){
        super("myThread");
    }

    //intent:onStartCommand传来的intent
    @Override
    protected void onHandleIntent(Intent intent) {
        String name = intent.getStringExtra("name");//获得activity传来的"name"
        Log.i("MyIntentService", "MyIntentService: onHandleIntent:"+name);

        try {
            Thread.sleep(15000); //测试是否在子线程中执行？
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("MyIntentService", "MyIntentService: onHandleIntent:执行完毕");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //IntentService内部会自动销毁服务（在执行完任务后），这里验证下是否销毁
        Log.i("MyIntentService", "MyIntentService: onDestroy");
    }
}
