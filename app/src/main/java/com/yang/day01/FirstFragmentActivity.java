package com.yang.day01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yang.fragment.StaticFragment;

public class FirstFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_fragment);

        //不能进行像动态fragment那样的传值方式，可以先获得fragment的View，然后根据View对控件进行操作
        StaticFragment staticFragment = (StaticFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_first);

        View v = staticFragment.getView();
        TextView tv_userName = (TextView) v.findViewById(R.id.tv_userName);
        tv_userName.setText("这里是传的值");

    }
}
