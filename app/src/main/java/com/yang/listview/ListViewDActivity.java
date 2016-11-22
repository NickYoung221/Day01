package com.yang.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.yang.adapter.MyBaseAdapter;
import com.yang.day01.R;
import com.yang.entity.Student;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ListView: BaseAdapter
 */
public class ListViewDActivity extends AppCompatActivity {
    ListView lv;
    List<Student> stus = new ArrayList<Student>();
    MyBaseAdapter myBaseAdapter;

    @InjectView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_d);
        ButterKnife.inject(this);

        lv = (ListView) findViewById(R.id.lv_d);
        //stus添加数据：模拟从服务器返回的学生集合
        stus.add(new Student("张三", 1, 21));
        stus.add(new Student("李四", 2, 22));
        stus.add(new Student("王五", 3, 23));
        stus.add(new Student("小明", 4, 24));
        stus.add(new Student("小明", 5, 25));
        stus.add(new Student("小明", 6, 26));
        stus.add(new Student("王小明", 7, 27));
        stus.add(new Student("王小明", 8, 28));
        stus.add(new Student("李小明", 9, 29));
        stus.add(new Student("李小明", 10, 30));
        stus.add(new Student("张小明", 11, 31));
        stus.add(new Student("张小明", 12, 32));

        //设置list显示在R.layout.simple_item上
        //调用MyBaseAdapter
        myBaseAdapter = new MyBaseAdapter(stus, this);
        lv.setAdapter(myBaseAdapter);

        //设置listview的item点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //lv,item,点击的item所在的位置（从0开始）,item的id
                Intent intent = new Intent(ListViewDActivity.this, ListViewDetailsActivity.class);
                //传一个student对象:点击的item显示的数据
                Student student = stus.get(position);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn)
    public void onClick() {
        //改变List数据
        stus.add(new Student("赵小明", 13, 33));
        stus.add(new Student("赵小明", 14, 34));
        stus.add(new Student("周小明", 15, 35));
        stus.add(new Student("周小明", 16, 36));

        //若myBaseAdapter!=null,直接更新，否则再new一个myBaseAdapter
        if(myBaseAdapter!=null){
            //更新页面
            myBaseAdapter.notifyDataSetChanged();
        }else{
            myBaseAdapter = new MyBaseAdapter(stus,this);
            lv.setAdapter(myBaseAdapter);
        }

    }




}
