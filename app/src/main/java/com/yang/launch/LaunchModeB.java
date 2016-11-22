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
 * B启动方式：singleTop，若需要启动的activity本身就处于栈顶，则不再创建新对象
 */
public class LaunchModeB extends AppCompatActivity {
    TextView tv_message_B;
    @InjectView(R.id.tv_message_B)
    TextView tvMessageB;
    @InjectView(R.id.btn_bToA)
    Button btnBToA;
    @InjectView(R.id.btn_bToB)
    Button btnBToB;
    @InjectView(R.id.btn_bToC)
    Button btnBToC;
    @InjectView(R.id.btn_bToD)
    Button btnBToD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_b);
        ButterKnife.inject(this);

        tv_message_B = (TextView) findViewById(R.id.tv_message_B);
        tv_message_B.setText(this + ",taskId:" + this.getTaskId());

    }

    @OnClick({R.id.btn_bToA, R.id.btn_bToB, R.id.btn_bToC, R.id.btn_bToD})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bToA:
                Intent intent1 = new Intent(this,LaunchModeA.class);
                startActivity(intent1);
                break;
            case R.id.btn_bToB:
                Intent intent2 = new Intent(this,LaunchModeB.class);
                startActivity(intent2);
                break;
            case R.id.btn_bToC:
                Intent intent3 = new Intent(this,LaunchModeC.class);
                startActivity(intent3);
                break;
            case R.id.btn_bToD:
                Intent intent4 = new Intent(this,LaunchModeD.class);
                startActivity(intent4);
                break;
        }
    }
}
