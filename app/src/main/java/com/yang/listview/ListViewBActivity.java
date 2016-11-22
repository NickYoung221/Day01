package com.yang.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yang.day01.R;

/**
 * 使用ArrayAdapter设置listview的数据源
 */
public class ListViewBActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_b);

        listView = (ListView) findViewById(R.id.lv_b);
        //获取资源文件里的array数据
        String[] hobbies = getResources().getStringArray(R.array.hobbies);
        //this：上下文环境  android.R.layout.simple_list_item_1 ：系统自带的布局  hobbies：array数组
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,hobbies));



    }
}
