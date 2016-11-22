package com.yang.interesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.org.rocko.bpb.BounceProgressBar;
import com.yang.day01.R;
import com.yang.interesting.loadingview.DialogDemoActivity;
import com.yang.interesting.loadingview.ViewDemoActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * interesting!
 */
public class InterestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_toSpinMenu,R.id.tv_toSubmitButton,R.id.tv_toViewDemo,R.id.tv_toDialogDemo,R.id.tv_toBounceProgressBar})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_toSpinMenu:
                Intent intent = new Intent(this,SpinMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_toSubmitButton:
                Intent intent1 = new Intent(this,SubmitButtonActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_toViewDemo:
                Intent intent2 = new Intent(this, ViewDemoActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_toDialogDemo:
                Intent intent3 = new Intent(this, DialogDemoActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_toBounceProgressBar:
                Intent intent4 = new Intent(this, BounceProgressBarActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
