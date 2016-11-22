package com.yang.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yang on 2016/9/27 0027.
 */
public class DBOpenHelper extends SQLiteOpenHelper{

    public DBOpenHelper(Context context,String name,int version){
        super(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DbOpenHelper", "DbOpenHelper: onCreate");

        String sql = "create table student(_id integer primary key autoincrement,name text)";
        String sql1 ="create table class(_id integer primary key autoincrement,name text)";
        db.execSQL(sql);
        db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库版本升级的时候会调用该方法
        Log.i("DbOpenHelper", "DbOpenHelper: onUpgrade");
    }



}
