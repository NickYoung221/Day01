package com.yang.day01;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yang.fragment.DyncFragment;
import com.yang.fragment.SendToActivityFragment;
import com.yang.fragment.StaticFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示或隐藏动态fragment，fragment里面的状态不变，只是被隐藏了
 */
public class HideShowFragmentActivity extends AppCompatActivity implements View.OnClickListener{

    int preIndex;   //标示前一次点击按钮的index
    //fragment的集合
    List<Fragment> fragmentList = new ArrayList<Fragment>();

    Button btn_showFragment1;
    Button btn_showFragment2;
    Button btn_showFragment3;
    StaticFragment staticFragment = new StaticFragment();
    DyncFragment dyncFragment = new DyncFragment();
    SendToActivityFragment sendToActivityFragment = new SendToActivityFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_show_fragment);

        btn_showFragment1 = (Button) findViewById(R.id.btn_showFragment1);
        btn_showFragment2 = (Button) findViewById(R.id.btn_showFragment2);
        btn_showFragment3 = (Button) findViewById(R.id.btn_showFragment3);

        btn_showFragment1.setOnClickListener(this);
        btn_showFragment2.setOnClickListener(this);
        btn_showFragment3.setOnClickListener(this);


        fragmentList.add(staticFragment);
        fragmentList.add(dyncFragment);
        fragmentList.add(sendToActivityFragment);

        FragmentManager fragmentManager  = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fl_hideOrShow,fragmentList.get(preIndex));
        fragmentTransaction.commit();

    }

    @Override
    public void onClick(View v) {

        /*switch (v.getId()){

            case R.id.btn_showFragment1:    //显示第一个fragment（这里是staticFragment）
                *//** 1、第一次显示：add();新创建
                 * /2、再次显示：show();不是新创建
                 *//*
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //判断fragment是否已经add
                if(staticFragment.isAdded()){
                    //若添加过，则直接显示staticFragment
                    fragmentTransaction.show(staticFragment).hide(dyncFragment).hide(sendToActivityFragment);
                }else{
                    //若没有添加过，则添加staticFragment
                    // 调用的是add()方法，并不是replace()方法，replace()方法是销毁重建,add后还要show出来，hide其他
                    fragmentTransaction.add(R.id.fl_hideOrShow,staticFragment).show(staticFragment).hide(dyncFragment).hide(sendToActivityFragment);
                }

                fragmentTransaction.commit();
                break;
            case R.id.btn_showFragment2:    //显示第二个fragment（这里是dyncFragment）
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                //判断fragment是否add
                if(dyncFragment.isAdded()){
                    //同理上面
                    fragmentTransaction2.show(dyncFragment).hide(staticFragment).hide(sendToActivityFragment);
                }else{
                    fragmentTransaction2.add(R.id.fl_hideOrShow,dyncFragment).show(dyncFragment).hide(staticFragment).hide(sendToActivityFragment);

                }

                fragmentTransaction2.commit();
                break;
            case R.id.btn_showFragment3:
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                if(sendToActivityFragment.isAdded()){
                    fragmentTransaction3.show(sendToActivityFragment).hide(staticFragment).hide(dyncFragment);

                }else{
                    fragmentTransaction3.add(R.id.fl_hideOrShow,sendToActivityFragment).show(sendToActivityFragment).hide(staticFragment).hide(dyncFragment);
                }

                fragmentTransaction3.commit();
                break;

        }*/   //要对该方法进行改进,改进如下：
        //改进具体思路：把fragment放到集合里，preIndex:前一个fragment的index，currentIndex：当前需要显示的fragment的index
        // 写一个方法，作用是show当前的fragment，hide上一个fragment，然后根据点击不同的按钮，调用该方法的参数也不一样

        int currentIndex = 0;   //当前选中的按钮的index
        switch (v.getId()){
            case R.id.btn_showFragment1:
                currentIndex = 0;
                break;
            case R.id.btn_showFragment2:
                currentIndex = 1;
                break;
            case R.id.btn_showFragment3:
                currentIndex = 2;
                break;
        }
        //操作：前一个按钮取消选中，新按钮选中；前一个fragment隐藏，新的显示(这里没有设置按钮选中与取消的样式)

        //如果两次点击是同一个按钮，则不执行操作
        if(currentIndex!=preIndex){
            toggleFragment(fragmentList.get(preIndex),fragmentList.get(currentIndex));
        }

        preIndex = currentIndex;//点击完一次按钮，则把currentIndex赋给preIndex，这样preIndex表示的就是上一次点击按钮的index
    }

    //切换fragment显示和隐藏的方法
    public void toggleFragment(Fragment hideFragment,Fragment showFragment){
        //如果两次显示的是同一个fragment（即用户点击同一个按钮），则不进行任何操作(由于上面执行操作的时候会进行判断，这里其实可以不用判断)
        if(hideFragment!=showFragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //首先，先隐藏上一个fragment(即hideFragment)
            fragmentTransaction.hide(hideFragment);
            //判断showFragment是否已经添加，若没有添加，则先要添加
            if(!showFragment.isAdded()){
                fragmentTransaction.add(R.id.fl_hideOrShow,showFragment);
            }
            //显示出showFragment
            fragmentTransaction.show(showFragment);

            fragmentTransaction.commit();
        }

    }





}
