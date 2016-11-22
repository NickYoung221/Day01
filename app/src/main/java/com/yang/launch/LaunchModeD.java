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
 * D启动方式：singleInstance:多个应用共享一个activity;启动activity时,新建一个返回栈存放
 */
public class LaunchModeD extends AppCompatActivity {
    TextView tv_message_D;
    @InjectView(R.id.tv_message_D)
    TextView tvMessageD;
    @InjectView(R.id.btn_dToA)
    Button btnDToA;
    @InjectView(R.id.btn_dToB)
    Button btnDToB;
    @InjectView(R.id.btn_dToC)
    Button btnDToC;
    @InjectView(R.id.btn_dToD)
    Button btnDToD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_d);
        ButterKnife.inject(this);

        tv_message_D = (TextView) findViewById(R.id.tv_message_D);
        tv_message_D.setText(this + ",taskId:" + this.getTaskId());
    }

    @OnClick({R.id.btn_dToA, R.id.btn_dToB, R.id.btn_dToC, R.id.btn_dToD})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_dToA:
                Intent intent1 = new Intent(this,LaunchModeA.class);
                startActivity(intent1);
                break;
            case R.id.btn_dToB:
                Intent intent2 = new Intent(this,LaunchModeB.class);
                startActivity(intent2);
                break;
            case R.id.btn_dToC:
                Intent intent3 = new Intent(this,LaunchModeC.class);
                startActivity(intent3);
                break;
            case R.id.btn_dToD:
                Intent intent4 = new Intent(this,LaunchModeD.class);
                startActivity(intent4);
                break;
        }
    }
}
