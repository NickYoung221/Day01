package com.yang.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yang.broadcast.SecondBroadcastReceiver;
import com.yang.day01.LoginActivity;
import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 定义一个定时任务，（可以进行页面跳转，发送广播）也可以定义周期性执行的任务，使用setRepeating()方法
 * 取消定义好的定时任务
 */
public class AlarmManagerActivity extends AppCompatActivity {

    @InjectView(R.id.btn_startAlarm)
    Button btnStartAlarm;
    @InjectView(R.id.btn_cancelAlarm)
    Button btnCancelAlarm;

    PendingIntent pendingIntent;
    Integer requestCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        ButterKnife.inject(this);


    }


    @OnClick({R.id.btn_startAlarm, R.id.btn_cancelAlarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startAlarm://开启定时任务
                //首先获取系统服务
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                //执行页面跳转
                //Intent intent = new Intent(this, LoginActivity.class);
                //pendingIntent = PendingIntent.getActivity(this,requestCode,intent,0);
                //发送广播
                //Intent intent = new Intent(this, SecondBroadcastReceiver.class);
                //pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent,0);
                //执行service,不需要开启service，会自动开启,但是不会destroy（在service里面设置弹出通知，因此这里会定期弹出通知）
                Intent intent = new Intent(this,FirstService.class);
                pendingIntent = PendingIntent.getService(this,requestCode,intent,0);

                /*  设置时间：当前时间，向后5s执行
                参数一：type：
                参数二：执行任务的时间（系统启动到现在的时间：SystemClock.elapsedRealtime()，从1970年1月1号开始到现在时间：System.currentTimeMillis()）
                参数三：PendingIntent
                */
                // type类型：从系统启动开始算：1）ELAPSED_REALTIME，系统休眠不会执行任务，2）ELAPSED_REALTIME_WAKEUP：休眠状态，也会唤醒；
                //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+5000,pendingIntent);
                // type类型：从1970年1月1号开始算：1）RTC，系统休眠不会执行任务，2）RTC_WAKEUP，系统休眠也会执行任务
                //alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000,pendingIntent);
                //setRepeating:周期性的执行一个任务
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000,10000,pendingIntent);
                Toast.makeText(AlarmManagerActivity.this, "闹钟开启", Toast.LENGTH_SHORT).show();
                Log.i("AlarmManagerActivity", "AlarmManagerActivity: onClick:闹钟开启");
                break;
            case R.id.btn_cancelAlarm://关闭已开启的定时服务
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.cancel(pendingIntent);
                Toast.makeText(AlarmManagerActivity.this, "闹钟关闭", Toast.LENGTH_SHORT).show();
                Log.i("AlarmManagerActivity", "AlarmManagerActivity: onClick:闹钟关闭");
                break;
        }
    }


}
