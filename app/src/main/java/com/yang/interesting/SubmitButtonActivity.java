package com.yang.interesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.spark.submitbutton.SubmitButton;
import com.yang.day01.R;

public class SubmitButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_button);

        SubmitButton sb = (SubmitButton) findViewById(R.id.btn3);
        if(sb!=null){
            sb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SubmitButtonActivity.this, "111", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
