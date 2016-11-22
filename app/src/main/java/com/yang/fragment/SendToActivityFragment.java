package com.yang.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yang.day01.LoginActivity;
import com.yang.day01.MyActivity;
import com.yang.day01.R;

/**
 * 从fragment传值给activity（需要用到回调机制）
 * Created by yang on 2016/9/18 0018.
 */
public class SendToActivityFragment extends Fragment {
    //内部接口
    public interface OnSendMessageListener{
        void OnSendMessage(String value);
    }
    OnSendMessageListener onSendMessageListener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //判断fragment嵌入的activity是否实现OnSendMessageListener接口
        if(getActivity() instanceof OnSendMessageListener){
            onSendMessageListener = (OnSendMessageListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_send_to_activity,null);

        //传给嵌入的activity
        Button btn_submit = (Button) v.findViewById(R.id.btn_submit);
        final EditText et_input = (EditText) v.findViewById(R.id.et_input);
        Button btn_toLoginActivity = (Button) v.findViewById(R.id.btn_toLoginActivity);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得EditText输入的内容
                String value = et_input.getText().toString();
                //
                if(onSendMessageListener!=null){
                    //onSendMessageListener.OnSendMessage(value);  不采用这个方式,用下面的方式
                    setOnSendMessageListener(onSendMessageListener,value);
                }

            }
        });

        //点击按钮跳转到MyActivity并传值给它（即fragment和其他的activity传值），通过Intent实现
        btn_toLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyActivity.class);//使用getActivity()方法
                String value = et_input.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("value",value);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });



        return v;
    }


    public void setOnSendMessageListener(OnSendMessageListener onSendMessageListener,String value){
        onSendMessageListener.OnSendMessage(value);
    }


}
