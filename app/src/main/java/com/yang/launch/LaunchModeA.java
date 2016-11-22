package com.yang.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yang.day01.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A启动方式：默认，即Standard：默认创建一个新的activity对象
 */
public class LaunchModeA extends AppCompatActivity {
    TextView tv_message_A;

    @InjectView(R.id.tv_message_A)
    TextView tvMessageA;
    @InjectView(R.id.btn_aToA)
    Button btnAToA;
    @InjectView(R.id.btn_aToB)
    Button btnAToB;
    @InjectView(R.id.btn_aToC)
    Button btnAToC;
    @InjectView(R.id.btn_aToD)
    Button btnAToD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_a);
        ButterKnife.inject(this);
        //显示当前Activity信息以及TaskId
        tv_message_A = (TextView) findViewById(R.id.tv_message_A);
        tv_message_A.setText(this + ",taskId:" + this.getTaskId());

    }

    @OnClick({R.id.btn_aToA, R.id.btn_aToB, R.id.btn_aToC, R.id.btn_aToD})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_aToA:
                Intent intent1 = new Intent(this,LaunchModeA.class);
                startActivity(intent1);
                break;
            case R.id.btn_aToB:
                Intent intent2 = new Intent(this,LaunchModeB.class);
                startActivity(intent2);
                break;
            case R.id.btn_aToC:
                Intent intent3 = new Intent(this,LaunchModeC.class);
                startActivity(intent3);
                break;
            case R.id.btn_aToD:
                Intent intent4 = new Intent(this,LaunchModeD.class);
                startActivity(intent4);
                break;
        }
    }
}
