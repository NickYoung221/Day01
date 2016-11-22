package com.yang.callback;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by yang on 2016/9/18 0018.
 */
public class MyActivity implements MyOnClickListener{
    @Override
    public void onMyClick() {
        Log.i("MyActivity", "onMyClick: ");
    }
}
