package com.yang.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yang.day01.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SQLiteActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @InjectView(R.id.btn_SQLite_insert)
    Button btnSQLiteInsert;
    @InjectView(R.id.btn_SQLite_delete)
    Button btnSQLiteDelete;
    @InjectView(R.id.btn_SQLite_update)
    Button btnSQLiteUpdate;
    @InjectView(R.id.btn_SQLite_query)
    Button btnSQLiteQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        ButterKnife.inject(this);


        //创建MyDataBaseHelper对象
        int version_1 = 2;  //数据库版本号
        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this, "myappdb1", version_1);
        /**
         * 创建数据库
         *    判断：数据库不存在，创建；==>onCreate
         *          数据库已经存在，什么都不做；不会再调用oncreate;
         */
        db = myDataBaseHelper.getReadableDatabase();


    }

    @OnClick({R.id.btn_SQLite_insert, R.id.btn_SQLite_delete, R.id.btn_SQLite_update, R.id.btn_SQLite_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_SQLite_insert://向数据库插入信息
                //方式一：由于_id设置的是自增，所以这里只需要对name和sex字段赋值，格式如下：
//                String insertSql = "insert into student(name,sex) values('张三','男')";
//                db.execSQL(insertSql);   //执行sql语句

                //方式二：
                ContentValues contentValues = new ContentValues();
                contentValues.put("name","张三");  // 列名-列取值
                contentValues.put("sex","男");
                /**
                 * 参数一：表名
                 * 参数二：若contentvalues为null,转换成insert时，插入"temp"这一列,eg:db.insert("student","temp",contentValues);
                 * 在保证contentvalues不为null，这里可以设为null
                 * 参数三：contentvalues:列名-列值
                 */
                db.insert("student",null,contentValues);
                break;
            case R.id.btn_SQLite_delete://删除id为2的记录
                //方式一：
//                String deleteSql = "delete from student where _id = 2 ";
//                db.execSQL(deleteSql);
                //方式二：
                /**  参数一：表名
                 *  参数二：删除条件
                 * 参数三：参数二中？的取值(是字符串数组)
                 */
                db.delete("student","_id=?",new String[]{"2"});
                break;
            case R.id.btn_SQLite_update://修改id为1的记录的姓名
                //方式一：
//                String updateSql = "update student set name = '张三1' where _id = 1 ";
//                db.execSQL(updateSql);
                //方式二：
                /** 修改_id=1&sex='男'的学生的姓名（张三1）
                 *      参数一：表名
                 *      参数二：set (字段-字段取值)
                 *      参数三：where条件
                 *      参数四：参数三中的？的取值
                 */
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("name","张三1");
                db.update("student",contentValues2,"_id = ? and sex = ?",new String[]{"1","男"});
                break;
            case R.id.btn_SQLite_query://查询表中记录
                //方式一：
//                String querySql = "select * from student";
                /**
                 * 参数一：查询语句
                 * 参数二：sql语句中？对应的取值
                 */
//                Cursor cursor = db.rawQuery(querySql,null);

                //方式二：
                /**
                 * 参数一：表名
                 * 参数二：查询字段
                 * 参数三：查询条件
                 * 参数四：参数三中？的赋值
                 * 剩余参数：groupBy  having  orderBy  具体可看方法描述
                 */
                Cursor cursor = db.query("student",new String[]{"_id","name","sex"},null,null,null,null,null);
//                Cursor cursor = db.query("student",new String[]{"_id","name","sex"},"_id=?",new String[]{"3"},null,null,null);

                //SimpleCursorAdapter==>显示在listview？？
                //cursor转换为List<StudentDB>
                List<StudentDB> studentDBList = new ArrayList<StudentDB>();
                while(cursor.moveToNext()){
                    //根据列名取出列号，再根据列号取出信息
                    Integer id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String sex = cursor.getString(cursor.getColumnIndex("sex"));
                    StudentDB studentDB = new StudentDB(id,name,sex);
                    studentDBList.add(studentDB);
                }
                //遍历studentDBList，并打出日志
                for(StudentDB studentDB:studentDBList){
                    Log.i("SQLiteActivity", "SQLiteActivity: onClick:"+studentDB);
                }
                cursor.close();//close cursor
                break;
        }
    }




}
