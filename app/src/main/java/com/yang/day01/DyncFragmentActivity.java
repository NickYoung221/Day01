package com.yang.day01;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yang.fragment.DyncFragment;
import com.yang.fragment.StaticFragment;

public class DyncFragmentActivity extends AppCompatActivity {
    Button btn_changeFragment;
    EditText et_input;
    Button btn_sendToFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dync_fragment);

        btn_changeFragment = (Button) findViewById(R.id.btn_changeFragment);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_sendToFragment = (Button) findViewById(R.id.btn_sendToFragment);


        //getFragmentManager().beginTransaction(); //一般用getSupportFragmentManager()
        //创建事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DyncFragment dyncFragment = new DyncFragment();
        //资源文件，fragment,tag
        fragmentTransaction.replace(R.id.fl_dync,dyncFragment);
        //提交事务
        fragmentTransaction.commit();

        btn_changeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击按钮后替换为另一个fragment（创建事务->替换fragment->提交事务）
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                StaticFragment staticFragment = new StaticFragment();
                fragmentTransaction.replace(R.id.fl_dync,staticFragment);
                fragmentTransaction.commit();
            }
        });

        //点击按钮给fragment传值
        btn_sendToFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                DyncFragment dyncFragment = new DyncFragment();
                fragmentTransaction.replace(R.id.fl_dync,dyncFragment);
                String value = et_input.getText().toString();
                //将需要传的信息放在bundle里面，最后调用.setArguments();方法
                Bundle bundle = new Bundle();
                bundle.putString("value",value);
                dyncFragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });

    }
}
