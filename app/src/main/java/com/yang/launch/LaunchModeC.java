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
 * C启动方式：singleTask，需要启动的activity,在栈中存在，不再创建新的；
 * 如果当前activity不在栈顶，销毁当前activity上面的所有activity；
 */
public class LaunchModeC extends AppCompatActivity {
    TextView tv_message_C;
    @InjectView(R.id.tv_message_C)
    TextView tvMessageC;
    @InjectView(R.id.btn_cToA)
    Button btnCToA;
    @InjectView(R.id.btn_cToB)
    Button btnCToB;
    @InjectView(R.id.btn_cToC)
    Button btnCToC;
    @InjectView(R.id.btn_cToD)
    Button btnCToD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode_c);
        ButterKnife.inject(this);

        tv_message_C = (TextView) findViewById(R.id.tv_message_C);
        tv_message_C.setText(this + ",taskId:" + this.getTaskId());
    }

    @OnClick({R.id.btn_cToA, R.id.btn_cToB, R.id.btn_cToC, R.id.btn_cToD})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cToA:
                Intent intent1 = new Intent(this,LaunchModeA.class);
                startActivity(intent1);
                break;
            case R.id.btn_cToB:
                Intent intent2 = new Intent(this,LaunchModeB.class);
                startActivity(intent2);
                break;
            case R.id.btn_cToC:
                Intent intent3 = new Intent(this,LaunchModeC.class);
                startActivity(intent3);
                break;
            case R.id.btn_cToD:
                Intent intent4 = new Intent(this,LaunchModeD.class);
                startActivity(intent4);
                break;
        }
    }
}
