package com.yang.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 注意：广播接收者里面不能执行耗时任务，（会报ANR异常 application not response）也不能开子线程，
 */
public class BroadcastActivity extends AppCompatActivity {

    DyncBroadcastReceiver dyncBroadcastReceiver;//动态广播接收者
    boolean flag = false;   //ture表示已注册，false表示未注册,默认是没有注册

    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.button2)
    Button button2;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button4)
    Button button4;
    @InjectView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:  //发送一个静态广播，在清单文件中配置
                Intent intent = new Intent();
                intent.putExtra("name","张三");
                intent.setAction("com.yang.action1");
                sendBroadcast(intent);//发送广播
                break;
            case R.id.button2: //注册一个动态广播，在代码中配置action属性
                dyncBroadcastReceiver = new DyncBroadcastReceiver();

                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.yang.action2");
                //指定DyncBroadcastReceiver接收action:com.geminno.action2的广播
                registerReceiver(dyncBroadcastReceiver, intentFilter);//注册广播
                flag = true;//标记该动态广播已注册
                Log.i("BroadcastReceiver", "onClick: 注册动态广播");
                break;
            case R.id.button3://发送动态广播，DyncBroadcastReceiver接收(先注册，再发送)
                Intent intent2 = new Intent();
                intent2.setAction("com.yang.action2");
                sendBroadcast(intent2);
                break;
            case R.id.button4://解除动态广播注册
                //要进行判断，若注册过，则解除注册，若没注册，则什么也不做
                if(flag){
                    //解除注册的广播
                    unregisterReceiver(dyncBroadcastReceiver);
                    flag = false;
                    Log.i("BroadcastReceiver", "onClick: 解除注册动态广播");
                } else{
                    Log.i("BroadcastReceiver", "onClick: 不能解除注册");
                }
                break;
            case R.id.button5://发送有序广播，在配置文件intent-filter标签里设置android:priority="1"，参数越大，优先级越高
                Intent intent3 = new Intent();
                intent3.putExtra("name","有序广播");
                intent3.setAction("com.yang.action1");
                //发送有序广播，参数二：设置权限，规定有权限的接受者，才能接受广播，这里设为null(同理也可以在广播接收者里设置权限，只接收有权限的广播)
                sendOrderedBroadcast(intent3,null);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(flag){
            unregisterReceiver(dyncBroadcastReceiver);//取消动态广播的注册
        }
    }



}
