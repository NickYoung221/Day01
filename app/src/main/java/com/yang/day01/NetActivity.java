package com.yang.day01;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.yang.entity.User;
import com.yang.util.NetUtil;
import com.yang.util.PathUrl;

import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络通信（不使用框架，使用自己定义的工具类NetUtil）
 */
public class NetActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_login;
    EditText eT_name;
    EditText eT_pwd;
    ImageView iV_head;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //标识为1的这条信息
            if(msg.what==1){  //get请求方式传来的message
                String str = (String) msg.obj;
                //如果NetUtil里面message传来的不是"响应失败！-get"（未连接到服务器）和"登录失败-get"（用户名密码错误）才往下执行
                if(!str.equals("响应失败！-get") && !str.equals("登录失败-get")) {
                    Gson gson = new Gson();
                    //服务器返回的是一个json对象，这里要把json对象转换为user
                    User user = gson.fromJson(str, User.class);
                    if (user != null) {
                        btn_login.setText(user.getUserName() + ":" + user.getUserPwd());//改变登录按钮的文本
                        String userImageUrl = PathUrl.serverUrl + user.getUserImageUrl();
                        //根据userImageUrl将用户头像显示在ImageView上面
                        x.image().bind(iV_head, userImageUrl);

                    }
                }
            }else if(msg.what==2){  //post请求方式传来的message
                String str = (String) msg.obj;
                btn_login.setText(str);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);

        eT_name = (EditText) findViewById(R.id.eT_name);
        eT_pwd = (EditText) findViewById(R.id.eT_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        iV_head = (ImageView) findViewById(R.id.iV_head);

        btn_login.setOnClickListener(this);
        iV_head.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:  //get方式
                String userName = eT_name.getText().toString();
                String userPwd = eT_pwd.getText().toString();
                String url = PathUrl.appUrl+"/LoginServlet?userName="+userName+"&userPwd="+userPwd;//cmd- ipconfig
                NetUtil.getDataByGet(url,handler);
                break;
            case R.id.iV_head:   //post方式
                String userName2 = eT_name.getText().toString();
                String userPwd2 = eT_pwd.getText().toString();
                String url2 = PathUrl.appUrl+"/LoginServlet";
                //String params = "userName="+userName2+"&userPwd="+userPwd2;
                //方式二：传map
                Map<String,Object> params = new HashMap<String,Object>();
                params.put("userName",userName2);
                params.put("userPwd",userPwd2);

                NetUtil.getDataByPost(url2,params,handler);
                break;
        }


    }
}
