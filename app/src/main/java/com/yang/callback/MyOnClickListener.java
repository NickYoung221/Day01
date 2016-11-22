package com.yang.callback;

/**
 * 回调机制:
 *      接口A：方法a();
 *      实现类B实现A：实现方法a();
 *      类C: 方法c(A a)===>调用a()
 * Created by yang on 2016/9/18 0018.
 */
public interface MyOnClickListener {
    public void onMyClick();
}
