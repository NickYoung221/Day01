package com.yang.day01;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DrawerLayoutActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ListView lv_slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.tb);
        lv_slider = (ListView) findViewById(R.id.lv_slider);
        toolbar.setLogo(R.mipmap.music);
        //设置toolBar为本activity的actionBar
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        //1：设置导航栏图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //2：调用了navigation icon 的点击事件       各参数含义？？？
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //修改actionBar的标题
                getSupportActionBar().setTitle("打开状态");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("关闭状态");
            }
        };

        //4：设置drawerLayout打开或关闭事件
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //设置ListView的item点击事件
        lv_slider.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //设置item点击事件：点击item时，关闭DrawerLayout
                switch (position){
                    case 0:
                        Intent intent = new Intent(DrawerLayoutActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(DrawerLayoutActivity.this,MyActivity.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(DrawerLayoutActivity.this,MainActivity.class);
                        startActivity(intent3);
                        break;
                }
                //drawerLayout.closeDrawers();
            }
        });

        //设置menu图标点击事件(这里是根据title属性来判断用户点击的是哪一个item,一般是根据id来判断:item.getItemId())
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getTitle().toString()) {
                    case "第一个":
                        Intent intent3 = new Intent(DrawerLayoutActivity.this,MyActivity.class);
                        startActivity(intent3);
                        break;
                    case "第二个":
                        Intent intent = new Intent(DrawerLayoutActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                    case "第三个":
                        Intent intent2 = new Intent(DrawerLayoutActivity.this,MainActivity.class);
                        startActivity(intent2);
                        break;
                }
                return false;
            }
        });


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        /**  3：修改导航图标
         *        一：actionBarDrawerToggle 和 drawerLayout 状态同步
         *        二：drawerLayout 当前状态，修改导航图标
         */
        actionBarDrawerToggle.syncState();  //同步状态

    }


    //设置toolBar的显示菜单menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
