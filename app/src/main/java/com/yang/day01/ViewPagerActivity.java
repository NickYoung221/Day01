package com.yang.day01;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        List<View> views = new ArrayList<View>();//添加的是viewpager中显示的view对象
        //找到viewpager
        vp = (ViewPager) findViewById(R.id.vp_guide);

        //解析布局文件，得到view
        LayoutInflater layoutInflater = LayoutInflater.from(this);//获得一个解析器，把布局解析为view
        View v1 = layoutInflater.inflate(R.layout.viewpager_1,null);//null表示不指定父view
        View v2 = layoutInflater.inflate(R.layout.viewpager_2,null);
        View v3 = layoutInflater.inflate(R.layout.viewpager_3,null);
        //将view添加到集合views中
        views.add(v1);
        views.add(v2);
        views.add(v3);


        vp.setAdapter(new MyPageAdapter(views));

    }

    //由于PagerAdapter是一个抽象类，所以需要创建一个MyPageAdapter来继承PagerAdapter
    class MyPageAdapter extends PagerAdapter{
        List<View> views;
        public MyPageAdapter(List<View> views){
            this.views = views;
        }

        /*
        初始化Item,container(viewpager)；
        返回：添加的view的key; 以view本身作为key
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //将position位置的view添加到容器
            container.addView(views.get(position));
            //return super.instantiateItem(container, position);   //此处也要重写
            return views.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            //容器中移除view
            container.removeView(views.get(position));
        }

        //viewpager显示view的个数
        @Override
        public int getCount() {
            return views.size();
        }

        //判断出view是不是我们需要显示的view
        @Override
        public boolean isViewFromObject(View view, Object object) {
            //return false;//true表示显示出，false表示不会显示，此处就不能直接返回false,显示会错乱
            return view==object;
        }
    }

}
