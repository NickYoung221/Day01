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
public class DyncFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //一般会解析一个文件，这里为了方便起见就设置一个TextView
//        TextView tv = new TextView(getActivity());
//        tv.setText("this is a dyncFragment");

        View v = inflater.inflate(R.layout.activity_login,null);
        TextView tV_find = (TextView) v.findViewById(R.id.tV_find);
        //获得Activity给fragment传的值
        Bundle bundle = getArguments();
        if(bundle!=null){
            String value = bundle.getString("value");
            tV_find.setText(value);
        }

        return v;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
