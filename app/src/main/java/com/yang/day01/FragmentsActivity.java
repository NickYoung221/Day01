package com.yang.day01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentsActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_ToFragmentSendValueToActivity;
    Button btn_ToDyncFragmentSendValueToActivity;
    Button btn_hideOrShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        btn_ToFragmentSendValueToActivity = (Button) findViewById(R.id.btn_ToFragmentSendValueToActivity);
        btn_ToDyncFragmentSendValueToActivity = (Button) findViewById(R.id.btn_ToDyncFragmentSendValueToActivity);
        btn_hideOrShow = (Button) findViewById(R.id.btn_hideOrShow);


        btn_ToFragmentSendValueToActivity.setOnClickListener(this);
        btn_ToDyncFragmentSendValueToActivity.setOnClickListener(this);
        btn_hideOrShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ToFragmentSendValueToActivity:
                Intent intent = new Intent(this,FragmentSendValueToActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ToDyncFragmentSendValueToActivity:
                Intent intent2 = new Intent(this,DyncFragmentSendValueToActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_hideOrShow:
                Intent intent3 = new Intent(this,HideShowFragmentActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
