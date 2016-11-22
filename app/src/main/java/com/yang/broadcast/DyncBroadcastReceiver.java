package com.yang.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/** 动态广播接收者，action属性需要在代码中配置
 * Created by yang on 2016/9/26 0026.
 */
public class DyncBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BroadcastReceiver", "onReceive: 动态态广播接受者：已接收广播");
    }
}
