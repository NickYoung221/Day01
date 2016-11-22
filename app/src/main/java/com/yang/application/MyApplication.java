package com.yang.application;

import android.app.Application;

import org.xutils.x;

/** 自定义的Application
 * Created by yang on 2016/9/12 0012.
 */
public class MyApplication extends Application{
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
