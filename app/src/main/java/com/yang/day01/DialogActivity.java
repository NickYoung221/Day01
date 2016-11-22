package com.yang.day01;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_dialog;
    Button btn_submitSex;
    RadioGroup rg_sex;
    String sex = "男";
    Button btn_hobby;
    Button btn_getFruit;
    Button btn_loginview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btn_dialog= (Button) findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示对话框
                new AlertDialog.Builder(DialogActivity.this).setIcon(R.mipmap.music).setTitle("请确认").setMessage("确定退出程序？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "用户确定", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "用户取消", Toast.LENGTH_SHORT).show();
                    }
                }).show();

            }
        });

        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);//获得RadioGroup
        //设置RadioGroup选中项发生变化的监听器
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //group:rg_sex,checkedId:选中的radiobutton的id
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_male){
                    sex="男";
                }else {
                    sex="女";
                }
            }
        });

        btn_submitSex = (Button) findViewById(R.id.btn_submitSex);
        btn_hobby = (Button) findViewById(R.id.btn_hobby);
        btn_getFruit = (Button) findViewById(R.id.btn_getFruit);
        btn_loginview = (Button) findViewById(R.id.btn_loginview);

        btn_submitSex.setOnClickListener(this);
        btn_hobby.setOnClickListener(this);
        btn_getFruit.setOnClickListener(this);
        btn_loginview.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submitSex:
                Toast.makeText(DialogActivity.this, "你选中的是："+sex, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_hobby:
                final String[] arrayHobbies = new String[]{"听音乐","打篮球","玩游戏","看电视"};//设置供用户选择的爱好的数组
                final boolean[] arrayHobbySelected = new boolean[]{true,true,false,false};//设置默认选中项的数组

                new AlertDialog.Builder(this).setTitle("你的爱好是？").setIcon(R.mipmap.music)
                        .setMultiChoiceItems(arrayHobbies, arrayHobbySelected, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                arrayHobbySelected[which] = isChecked;
                            }
                        }).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0;i<arrayHobbySelected.length;i++){
                            if(arrayHobbySelected[i]==true){
                                sb.append(arrayHobbies[i]+"、");
                            }
                        }
                        if(!TextUtils.isEmpty(sb)) {   //   TextUtils.isEmpty():判断字符串是否为空
                            sb.setLength(sb.length()-1);//将最后一个"、"去掉
                            Toast.makeText(DialogActivity.this, "你的爱好是：" + sb.toString(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DialogActivity.this, "你没有选择爱好", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.btn_getFruit:
                CheckBox cb_apple = (CheckBox) findViewById(R.id.cb_apple);
                CheckBox cb_banana = (CheckBox) findViewById(R.id.cb_banana);
                CheckBox cb_pear = (CheckBox) findViewById(R.id.cb_pear);

                String fruits = "";
                if(cb_apple.isChecked()){
                    fruits+=cb_apple.getText()+"、";
                }
                if(cb_banana.isChecked()){
                    fruits+=cb_banana.getText()+"、";
                }
                if(cb_pear.isChecked()){
                    fruits+=cb_pear.getText()+"、";
                }
                Toast.makeText(DialogActivity.this, fruits, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_loginview:
                //获得一个解析器，然后将布局文件解析为view,
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                //这里要设为final属性，因为在下面要获得该view里面的控件
                final View myLoginView = layoutInflater.inflate(R.layout.loginview,null);

                new AlertDialog.Builder(this).setTitle("用户登录").setIcon(R.mipmap.music)
                        .setView(myLoginView).setPositiveButton("登录",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //这里要想获得自定义view里面的控件，要在findViewById方法前加上自定义的view对象，
                        EditText et_loginview_user = (EditText) myLoginView.findViewById(R.id.et_loginview_user);
                        EditText et_loginview_pwd = (EditText) myLoginView.findViewById(R.id.et_loginview_pwd);
                        if(et_loginview_user!=null && et_loginview_pwd!=null) {
                            String user = et_loginview_user.getText().toString();
                            String pwd = et_loginview_pwd.getText().toString();
                            if (user != "" && pwd !="") {
                                Toast.makeText(DialogActivity.this, "用户名:" + user + "    密码：" + pwd, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;


        }
    }
}
