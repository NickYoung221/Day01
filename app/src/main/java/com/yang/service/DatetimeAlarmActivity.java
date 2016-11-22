package com.yang.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;

import com.yang.day01.LoginActivity;
import com.yang.day01.R;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/** 使用TimePickerDialog()
 * 点击按钮弹出选择时间的对话框，用户选择好时间后，在选定的时间时执行任务
 */
public class DatetimeAlarmActivity extends AppCompatActivity {

    int requestCode = 0;

    @InjectView(R.id.btn_setTime)
    Button btnSetTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datetime_alarm);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.btn_setTime)
    public void onClick() {
        /** 设置闹钟时间
         *
         *      参数二：设置完时间后的回调
         *      参数三：初始的小时  参数四：初始的分钟
         *      参数五：is24HourView 设置是否是24小时格式
         */
        //获取点击按钮（打开对话框）的当前时间
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.i("DatetimeAlarmActivity", "DatetimeAlarmActivity: onTimeSet:"+hourOfDay +",minute"+minute);

                //获取当前时间（用户设置好时间之后的）(主要是为了保持日期一致)
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar1.set(Calendar.MINUTE,minute);  // 这里的时间是执行任务的时间

                //开启定时任务
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Intent intent = new Intent(DatetimeAlarmActivity.this, LoginActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(DatetimeAlarmActivity.this,requestCode,intent,0);
                //getTimeInMillis() 获取时间的毫秒表示
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar1.getTimeInMillis(),pendingIntent);

            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();



    }
}
