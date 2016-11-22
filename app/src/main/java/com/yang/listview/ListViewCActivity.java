package com.yang.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yang.day01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 用SimpleAdapter设置数据源
 * context：上下文
 * List<? extends Map<String,?>> :数据源
 * int resource:布局
 * String[] from : map的column,key的集合
 * int[] to : from中的数据，应该显示在哪个控件上
 */
public class ListViewCActivity extends AppCompatActivity {
    List<HashMap<String,Object>> stus = new ArrayList<HashMap<String,Object>>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_c);

        lv = (ListView) findViewById(R.id.lv_c);
        //每循环一次，将一个学生对象放到map里面
        for(int i=0;i<3;){
            int j = i++;  //   i++和++i效果不一样
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("stuName","stuName"+j);
            map.put("stuId",j);
            map.put("stuAge",20+j);
            stus.add(map);
        }
        // String[] from={"name","sno"};
        SimpleAdapter adapter = new SimpleAdapter(this,stus,R.layout.simple_item,
                new String[]{"stuName","stuId","stuAge"},
                new int[]{R.id.tv_stuName,R.id.tv_stuId,R.id.tv_stuAge});
        //设置适配器
        lv.setAdapter(adapter);

    }
}
