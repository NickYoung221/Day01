package com.yang.day01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.yang.fragment.SendToActivityFragment;

/**
 * fragment传信息给activity(静态的fragment),实现自己定义的OnSendMessageListener接口
 */
public class FragmentSendValueToActivity extends AppCompatActivity implements SendToActivityFragment.OnSendMessageListener{
    TextView tv_receive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_send_value_to);

        tv_receive = (TextView) findViewById(R.id.tv_receive);
    }

    @Override
    public void OnSendMessage(String value) {
        tv_receive.setText(value);
    }
}
