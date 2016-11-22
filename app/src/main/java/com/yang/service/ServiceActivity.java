package com.yang.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * service是有优先级的，系统在kill service的时候，会按照优先级来kill的，优先级越低越容易被kill
 * 如何设置service不被kill？  http://jingyan.baidu.com/article/656db918938764e381249ce5.html
 *  1）设置Service在前台运行(Service.startForeground); 最高优先级
 *  2）设置优先级 在清单文件里设置android:priority="10"，值越大，优先级越高
 *  还有一些方法，参考网站http://blog.csdn.net/lonely_fireworks/article/details/18005971
 */
public class ServiceActivity extends AppCompatActivity {

    @InjectView(R.id.btn_startService)
    Button btnStartService;
    @InjectView(R.id.btn_stopService)
    Button btnStopService;
    @InjectView(R.id.btn_bindService)
    Button btnBindService;
    @InjectView(R.id.btn_unBindService)
    Button btnUnBindService;
    @InjectView(R.id.btn_toIntentService)
    Button btn_toIntentService;
    @InjectView(R.id.btn_toAlarmManager)
    Button btn_toAlarmManager;
    @InjectView(R.id.btn_toDatetimeAlarm)
    Button btn_toDatetimeAlarm;

    MyServiceConnection myServiceConnection = new MyServiceConnection();
    boolean flag = false;//true表示Service处于绑定状态，默认为false，即未绑定状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.inject(this);




    }

    @OnClick({R.id.btn_startService, R.id.btn_stopService, R.id.btn_bindService, R.id.btn_unBindService, R.id.btn_toIntentService,R.id.btn_toAlarmManager,R.id.btn_toDatetimeAlarm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_startService: //startService:开启一个服务
                Intent intent = new Intent(this,FirstService.class);
                startService(intent);
                break;
            case R.id.btn_stopService://stopService:停止一个服务
                Intent intent2 = new Intent(this,FirstService.class);
                stopService(intent2);
                break;
            case R.id.btn_bindService://bindService:绑定一个service
                Intent intent3 = new Intent(this,FirstService.class);
                //参数二：ServiceConnection（由于它是个接口，需要我们自定义一个类继承它）
                //参数三：bind service,服务没有创建,则自动创建
                bindService(intent3,myServiceConnection,BIND_AUTO_CREATE);
                flag = true;
                break;
            case R.id.btn_unBindService://unBindService:解除绑定的service
                if(flag) {
                    unbindService(myServiceConnection); //解除绑定
                    flag = false;
                }
                break;
            case R.id.btn_toIntentService://跳转到测试IntentService的页面
                Intent intent4 = new Intent(this,MyIntentServiceActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_toAlarmManager: //跳转到测试AlarmManager的页面
                Intent intent5 = new Intent(this,AlarmManagerActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_toDatetimeAlarm: //跳转到DatetimeAlarmActivity
                Intent intent6 = new Intent(this,DatetimeAlarmActivity.class);
                startActivity(intent6);
                break;
        }
    }

    class MyServiceConnection implements ServiceConnection{

        //IBinder service:onBind返回的值

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //服务连接上，会回调该方法
            Log.i("MyServiceConnection", "MyServiceConnection: onServiceConnected");
            FirstService.MyBinder myBinder = (FirstService.MyBinder) service;
            FirstService firstService = myBinder.getFirstService();
            String serviceName = firstService.getServiceName();
            Log.i("MyServiceConnection", "MyServiceConnection: onServiceConnected:获取service值："+serviceName);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //lost connection,回调; （当有异常情况，导致service stop时，会回调该方法）
            Log.i("MyServiceConnection", "MyServiceConnection: onServiceDisconnected");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity销毁的时候，bindservice创建的service,必须销毁（同时死),否则会报错
        if(flag){
            unbindService(myServiceConnection);
            flag = false;
        }
    }




}
