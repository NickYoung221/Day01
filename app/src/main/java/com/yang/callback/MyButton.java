package com.yang.callback;

/**
 * Created by yang on 2016/9/18 0018.
 */
public class MyButton {

    public void setOnClickListener(MyOnClickListener myOnClickListener){
        myOnClickListener.onMyClick();
    }


}
