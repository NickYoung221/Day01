package com.yang.day01;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yang.fragment.SendToActivityFragment;

/**
 * fragment传信息给activity(动态的fragment)
 */
public class DyncFragmentSendValueToActivity extends AppCompatActivity implements SendToActivityFragment.OnSendMessageListener {
    TextView tv_receive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dync_fragment_send_value_to);

        tv_receive = (TextView) findViewById(R.id.tv_receive);

        //创建事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        SendToActivityFragment sendToActivityFragment = new SendToActivityFragment();
        fragmentTransaction.replace(R.id.fl_dync_sendMessage,sendToActivityFragment);

        //提交事务
        fragmentTransaction.commit();

    }

    @Override     //回调机制具体实现？？
    public void OnSendMessage(String value) {
        tv_receive.setText(value);
    }
}
