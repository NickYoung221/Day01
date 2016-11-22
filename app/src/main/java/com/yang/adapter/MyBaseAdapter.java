package com.yang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yang.day01.R;
import com.yang.entity.Student;

import java.util.List;

/**
 * Created by yang on 2016/9/14 0014.
 */
public class MyBaseAdapter extends BaseAdapter {
    List<Student> stus;
    Context context;

    public MyBaseAdapter(List<Student> stus, Context context){
        this.stus = stus;
        this.context = context;
    }

    //返回：listview里有多少个item
    @Override
    public int getCount() {
        //不判断的话，若stus为null的话，则会出现空指针异常
        return (stus!=null)?stus.size():0;
    }

    //返回position位置的item数据对象
    @Override
    public Object getItem(int position) {
        return stus.get(position);
    }

    //返回item的标示：itemId
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * getView:显示一个item，创建一次；来回滚动，会多次调用
     * @param position: 位置
     * @param convertView ：当有itemA滚出的时候，下一个进来的itemB-->convertview(A的View)
     * @param parent : Listview
     * @return  某个item对应的view
     * 优化：减少解析布局的次数（具体办法：维持一个ViewHolder，从而对控件进行复用）
     *  如果convertView！=null;convertView作为当前item的View;
     *  如果convertView==null;解析布局文件
     *
     *  减少解析布局的次数：复用convertview
     *                      第5条item的convertView==第1条的View();
     *          优化后：解析布局次数=可见记录条数（+1）；
     *  View对象相同，findViewById()得到的控件对象也相同；第5条复用第一条的tv???
     *          解决方式：第1条findViewById()找到的对象==》保存起来（ViewHolder）
     *                    第5条，取出来；(convertView.getTag())
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        //getView执行：创建一个View
        //如果convertView==null,解析一个布局文件
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.simple_item, null);
            viewHolder = new ViewHolder();
            //找到控件
            TextView tv_stuName = (TextView) convertView.findViewById(R.id.tv_stuName);
            //Log.i("MyBaseAdapter", "MyBaseAdapter: getView:tvName"+tvName+",position:"+position);
            TextView tv_stuId = (TextView) convertView.findViewById(R.id.tv_stuId);
            TextView tv_stuAge = (TextView) convertView.findViewById(R.id.tv_stuAge);
            Button btn = (Button) convertView.findViewById(R.id.btn);
            //设置按钮点击事件
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
                }
            });

            //将view的控件对象保存到viewHolder
            viewHolder.tv_stuName = tv_stuName;
            viewHolder.tv_stuId = tv_stuId;
            viewHolder.tv_stuAge = tv_stuAge;
            viewHolder.btn = btn;
            //将converView和viewHolder建立关联（打一个tag，以方便下面寻找）
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /*  Log.i("MyBaseAdapter", "MyBaseAdapter: getView:"+position+":v:"+convertView);
       if(convertView!=null){
            Log.i("MyBaseAdapter", "MyBaseAdapter: convertView!=null"+position+",convertView:"+convertView);
        }*/
        //获取position位置的item，显示的学生信息
        Student stu = stus.get(position);

        //给控件赋值
        viewHolder.tv_stuName.setText(stu.getStuName());
        viewHolder.tv_stuId.setText(stu.getStuId()+""); // 直接显示Integer类型会出错，因此转换为String类型
        viewHolder.tv_stuAge.setText(stu.getStuAge()+"");

        //返回该view(即convertView)
        return convertView;
    }
    class ViewHolder{
        TextView tv_stuName;
        TextView tv_stuId;
        TextView tv_stuAge;
        Button btn;
    }
}
