package com.yang.net;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yang.day01.R;
import com.yang.util.PathUrl;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MyVolleyActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_return;
    Button btn_volley_get;
    Button btn_volley_post;
    Button btn_JSONObject_get;
    Button btn_JSONObject_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_volley);

        tv_return = (TextView) findViewById(R.id.tv_return);
        btn_volley_get = (Button) findViewById(R.id.btn_volley_get);
        btn_volley_post = (Button) findViewById(R.id.btn_volley_post);
        btn_JSONObject_get = (Button) findViewById(R.id.btn_JSONObject_get);
        btn_JSONObject_post = (Button) findViewById(R.id.btn_JSONObject_post);

        btn_volley_get.setOnClickListener(this);
        btn_volley_post.setOnClickListener(this);
        btn_JSONObject_get.setOnClickListener(this);
        btn_JSONObject_post.setOnClickListener(this);




    }


    @Override
    public void onClick(View v) {
        RequestQueue requestQueue = Volley.newRequestQueue(this); //只创建一个RequestQueue
        switch(v.getId()){
            case R.id.btn_volley_get: //StringRequest的用法：get方式

                //获取到一个RequestQueue对象
               // RequestQueue requestQueue = Volley.newRequestQueue(this);
                //创建一个StringRequest对象(get方式)
                StringRequest stringRequest = new StringRequest(PathUrl.appUrl+"/LoginServlet?userName=管理员&userPwd=123",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String str = "";
                                try {//解决乱码问题
                                    str = new String(response.getBytes("iso-8859-1"),"UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                Log.i("response", "onResponse: "+str);
                                tv_return.setText(str);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", "onErrorResponse: "+error);
                        tv_return.setText("响应失败！-get");
                    }
                });

                //将这个StringRequest对象添加到RequestQueue里面
                requestQueue.add(stringRequest);

                break;
            case R.id.btn_volley_post:  //StringRequest的用法：post方式
                //获取到一个RequestQueue对象 上面已经创建
                //RequestQueue requestQueue2 = Volley.newRequestQueue(this);
                //创建一个StringRequest对象(post方式)
                StringRequest stringRequest2 = new StringRequest(Request.Method.POST,PathUrl.appUrl+"/LoginServlet",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                String str = "";
//                                //解决乱码问题,这里由于在Servlet服务端设置了response.setContentType("text/xml;charset=UTF-8");
//                                //这里就不能用下面方式进行改码，使用下面方式反而会乱码！（原理？为什么get方式不受影响？get与post内部具体机制？）
//                                try {
//                                    str = new String(response.getBytes("iso-8859-1"),"UTF-8");
//                                } catch (UnsupportedEncodingException e) {
//                                    e.printStackTrace();
//                                }
//                                Log.i("response", "onResponse: "+str);
                                tv_return.setText(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", "onErrorResponse: "+error);
                        tv_return.setText("响应失败！-post");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String ,String> map = new HashMap<String, String>();
                        //userName=管理员&userPwd=123
                        map.put("userName","管理员");
                        map.put("userPwd","123");

                        return map;
                    }
                };

                //将这个StringRequest对象添加到RequestQueue队列里面
                requestQueue.add(stringRequest2);
                break;
            case R.id.btn_JSONObject_get:  //JsonRequest的用法（get）
                //创建一个JsonObjectRequest对象(get),第二个参数什么意思？？
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(PathUrl.appUrl+"/LoginServlet?userName=管理员&userPwd=123", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MyVolleyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyVolleyActivity.this, "读取失败-get", Toast.LENGTH_SHORT).show();
                        Log.i("MyVolleyActivity", "onErrorResponse: get");
                    }
                });

                //加入请求队列
                requestQueue.add(jsonObjectRequest);
                break;
            case R.id.btn_JSONObject_post: //JsonRequest的用法（post）
                //创建一个JsonObjectRequest对象(post)
                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, PathUrl.appUrl+"/LoginServlet", null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(MyVolleyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyVolleyActivity.this, "读取失败-post", Toast.LENGTH_SHORT).show();
                        Log.i("MyVolleyActivity", "onErrorResponse: post");
                    }
                }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("userName","管理员");
                        map.put("userPwd","123");

                        return map;
                    }
                };
                //加入请求队列
                requestQueue.add(jsonObjectRequest2);
                break;

        }
    }





}
