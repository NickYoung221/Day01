package com.yang.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/** 静态广播接收者二
 * Created by yang on 2016/9/26 0026.
 */
public class SecondBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getStringExtra("name");
        intent.putExtra("name","intent修改名字");//无效，或者说只在本广播接受者里有效
        Log.i("BroadcastReceiver", "onReceive: Second静态广播接受者：已接收广播,name:"+name);
        //有序广播可在优先级高的广播接收者里设置终止广播传递
        //abortBroadcast();//放弃广播，终止广播的传递

        //修改广播发送者传的内容:setResultExtras(必须是在有序广播里面才行)
        Bundle bundle = new Bundle();
        bundle.putString("name","1111");
        setResultExtras(bundle);


    }
}
