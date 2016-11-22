package com.yang.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/** 自定义一个类，继承SQLiteOpenHelper
 * Created by yang on 2016/9/25 0025.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper{

    public MyDataBaseHelper(Context context,String dataBaseName,int version){
        //参数一：上下文，参数二：数据库名称,参数三：CursorFactory(这里用系统自带的，所以传个null),参数四：数据库版本号
        super(context,dataBaseName,null,version);
    }

    //创建数据库的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表，主键一般写成“_id”,且为integer类型，自增，其他字段可以声明类型，也可以不声明
        String sql = "create table student(_id INTEGER primary key autoincrement,name text,sex)";

        db.execSQL(sql); //执行sql语句

        //数据库版本升级，若用户第一次安装，则不会执行onUpgrade方法，这里也要创建新增加的表class
        String sql2 = "create table class(_id integer primary key autoincrement,className text)";
        db.execSQL(sql2);
    }

    //升级数据库的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库版本--》2
        Log.i("MyDataBaseHelper", "onUpgrade: 版本升级");

        switch (oldVersion){
            case 1:
                //数据库版本升级，创建一张新表：class
                String sql = "create table class(_id integer primary key autoincrement,className text)";
                db.execSQL(sql);
                //break;  //不break，若还升级，则继续往下执行
        }

    }


}
