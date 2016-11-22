package com.yang.day01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * toolbar的使用：
 */
public class ToolbarActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        toolbar = (Toolbar) findViewById(R.id.tb);
        //设置Toolbar的属性
        toolbar.setSubtitle("sub title");
        toolbar.setTitle("title");
        toolbar.setLogo(R.mipmap.music);
        setSupportActionBar(toolbar);    //将自定义的Toolbar设为当前activity的ActionBar
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        //设置toolbar的导航图标点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarActivity.this, "你点击了导航栏图标", Toast.LENGTH_SHORT).show();
            }
        });
        //设置toolbar的menu item点击事件,若这里设置了，下面设置的按钮点击事件将不会执行
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(ToolbarActivity.this, "666", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });


    }

    //设置toolbar显示内容,即menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);//解析自己的布局文件
        return super.onCreateOptionsMenu(menu);
    }

    //设置某个item被选中的事件，若上面已经设置，则不会执行该方法，而调用上面的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer id = item.getItemId();
        String title = item.getTitle().toString();
        Toast.makeText(ToolbarActivity.this, id+title, Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


}
