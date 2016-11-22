package com.yang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yang.day01.R;

/**
 * Created by yang on 2016/9/17 0017.
 */
public class StaticFragment extends Fragment {

    // fragment创建的时候
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //返回 fragment显示的view
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getActivity():当前fragment嵌入的activity
//        TextView tv = new TextView(getActivity());
//        tv.setText("this is a static fragment");     //也可以自己解析一个文件

        //return super.onCreateView(inflater, container, savedInstanceState);
        View v =inflater.inflate(R.layout.loginview,null);

        return v;
    }
}
