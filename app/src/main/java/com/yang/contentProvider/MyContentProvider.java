package com.yang.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/** 自定义ContentProvider,同样需要在清单文件中注册(安卓四大组件都需要在配置文件中注册)
 *  要想使别的app也能访问该ContentProvider,需要在配置文件里配置：android:exported="true"
 * Created by yang on 2016/9/27 0027.
 */
public class MyContentProvider extends ContentProvider{

    int version = 1;
    DBOpenHelper dbOpenHelper;

    public static final int STUDENT = 1; //路径为：/student
    public static final int STUDENT_ID = 2; //路径为：/student/# (#代表只能是数字)
    public static final String AUTHORITY = "com.yang.day01.provider"; //权限(格式：包名.provider)

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);//？？

    static{ //在静态代码块的完成Uri的注册
        /**
         * 参数分别为：权限，路径，匹配结果码
         */
        sUriMatcher.addURI(AUTHORITY,"student",STUDENT);
        sUriMatcher.addURI(AUTHORITY,"student/#",STUDENT_ID);

    }



    @Override
    public boolean onCreate() {
        Log.i("MyContentProvider", "MyContentProvider: onCreate");
        dbOpenHelper = new DBOpenHelper(getContext(),"myappdb2",version);
        return true;  //这里要返回true
    }

    //查询数据库
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.i("MyContentProvider", "MyContentProvider: query");
        //获取数据库
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case STUDENT: //查询整张表
                Cursor cursor = db.query("student",projection,selection,selectionArgs,null,null,sortOrder);
                //uri注册观察者(用于在数据库修改后、实时更新lv显示的内容)
                if(getContext().getContentResolver()!=null){
                    cursor.setNotificationUri(getContext().getContentResolver(),uri);
                }
                return cursor;
            case STUDENT_ID: //根据id查询单条记录
                //content://com.yang.day01.provider/student/1
                long id = ContentUris.parseId(uri); //根据uri获取_id字段的信息
                //根据id查询：db.query("student",projection,"_id="+id,null,null,null,sortOrder);
                String mySelection = "";
                if(selection!=null){
                    Log.i("MyContentProvider", "query: id and selection");
                    mySelection = "_id="+id+" and "+selection;
                }else{
                    Log.i("MyContentProvider", "query: id");
                    mySelection = "_id="+id;
                }
                Cursor cursor2 = db.query("student",projection,mySelection,selectionArgs,null,null,sortOrder);
                //uri注册观察者
                if(getContext().getContentResolver()!=null) {
                    cursor2.setNotificationUri(getContext().getContentResolver(), uri);
                }
                return cursor2;
        }
        return null;
    }

    //获得该Uri的类型，（是整个表还是某条数据）
    // 必须以vnd开头，以路径结尾，后接.dir ，以id结尾，后接.item
    //最后还要接上 vnd.<authority><path>(权限和路径)
    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.i("MyContentProvider", "MyContentProvider: getType");
        switch (sUriMatcher.match(uri)){
            case STUDENT:
                return "vnd.android.cursor.dir/vnd."+AUTHORITY+".student";//表示的是整张表
            case STUDENT_ID:
                return "vnd.android.cursor.item/vnd."+AUTHORITY+".student";//表示的是某条记录
        }
        return null;
    }

    /** 向数据库里插入数据
     * content://com.yang.day01.provider/student/
     * @param uri
     * @param values
     * @return :新插入的记录，对应的Uri
     * content://com.yang.day01.provider/student/id
     */
    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i("MyContentProvider", "MyContentProvider: insert");
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();//获取数据库对象
        //Uri uri:根据uri，判断用户操作的是哪些数据
        int match = sUriMatcher.match(uri);
        switch (match){
            case STUDENT:  //对整张表进行操作
                long id = db.insert("student",null,values); //插入一条记录，返回的是该记录的主键
                //通知观察者有数据变化
                getContext().getContentResolver().notifyChange(uri,null);
                //返回新插入记录的Uri:整表Uri/id（ContentUris.withAppendedId()方法：将id加在uri后面作为新的uri）
                return ContentUris.withAppendedId(uri,id);

            case STUDENT_ID:  //对表中某条记录进行操作(不会有这种操作，可能是用户uri写错了，这里还是向整张表插入数据)
                long id2 = db.insert("student",null,values); //插入一条记录，返回的是该记录的主键
                //通知观察者有数据变化
                getContext().getContentResolver().notifyChange(uri,null);
                return ContentUris.withAppendedId(uri,id2);
        }

        return null;
    }

    //删除数据
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.i("MyContentProvider", "MyContentProvider: delete");
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case STUDENT:  //对整张表进行操作
                int num = db.delete("student",selection,selectionArgs);//返回删除记录的条数
                //通知观察者有记录变化
                getContext().getContentResolver().notifyChange(uri,null);
                return num;
            case STUDENT_ID: //对表中某条记录进行操作
                //解析出id
                long id = ContentUris.parseId(uri);
                //删除条件：id+selection
                String mySelection = "";
                if(selection!=null){
                    Log.i("MyContentProvider", "delete:id and selection");
                    mySelection = "_id="+id+" and "+selection;
                }else {
                    mySelection = "_id="+id;
                }
                int num2 = db.delete("student",mySelection,selectionArgs);
                //通知观察者有记录变化
                getContext().getContentResolver().notifyChange(uri,null);
                return num2;
        }
        return 0;
    }

    //更新数据
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.i("MyContentProvider", "MyContentProvider: update");
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case STUDENT://对整张表进行操作
                int num = db.update("student",values,selection,selectionArgs);//返回：更新的记录条数
                //通知观察者有记录变化
                getContext().getContentResolver().notifyChange(uri,null);
                return num;
            case STUDENT_ID:  //对表中某条记录进行操作
                //解析出id
                long id = ContentUris.parseId(uri);
                //删除条件：id+selection
                String mySelection = "";
                if(selection!=null){
                    Log.i("MyContentProvider", "delete:id and selection");
                    mySelection = "_id="+id+" and "+selection;
                }else {
                    mySelection = "_id="+id;
                }
                int num2 = db.update("student",values,mySelection,selectionArgs);
                //通知观察者有记录变化
                getContext().getContentResolver().notifyChange(uri,null);
                return num2;
        }
        return 0;
    }



}
