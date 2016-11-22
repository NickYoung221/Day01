package com.yang.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yang.day01.R;

/** 要在清单文件里面配置，安卓四大组件都需要在清单文件里面注册
 * Created by yang on 2016/9/27 0027.
 */
public class FirstService extends Service {

    String serviceName = "First服务";

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("FirstService", "FirstService: onBind");
        return new MyBinder();//这里的值会传给onServiceConnected，因此不能返回null，否则传过去的就是null
    }

    class MyBinder extends Binder {
        //定义自己的方法：
        //返回当前的service对象,这样在别的地方就可以通过该方法获得FirstService对象，因为我们不能new出来
        public FirstService getFirstService(){
            return FirstService.this;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("FirstService", "FirstService: onCreate");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("FirstService", "FirstService: onStartCommand");
        //这里让主线程sleep 25s，会报错，需要新开一个线程操作
        // Thread.sleep(25000);//service运行在主线程中，处理耗时任务：会报ANR错误

//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this).setSmallIcon(R.mipmap.mima)
                .setContentTitle("中奖通知").setContentText("恭喜你中奖了！").build();
        notificationManager.notify(1,notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FirstService", "FirstService: onDestroy");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("FirstService", "FirstService: onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("FirstService", "FirstService: onUnbind");
        //return super.onUnbind(intent);
        return true;// 返回true表示：其他条件满足，会执行onRebind方法,若不返回true，则无法调用到onRebind方法
    }


}
