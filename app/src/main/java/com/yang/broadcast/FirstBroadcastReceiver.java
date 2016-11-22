package com.yang.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/** 静态广播接收者一
 * Created by yang on 2016/9/26 0026.
 */
public class FirstBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getStringExtra("name");
        Log.i("BroadcastReceiver", "onReceive: First静态广播接受者：已接收广播,name:"+name);

        //可读取其他广播接收者修改后的数据，和接收其他广播接收者传的值
        Bundle bundle = getResultExtras(true);
        String changedname = bundle.getString("name");
        Log.i("BroadcastReceiver", "onReceive: First静态广播接受者：已接收广播,修改后的name:"+changedname);
    }
}
