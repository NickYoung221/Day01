package com.yang.net;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.yang.day01.R;
import com.yang.util.PathUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 异步任务
 */
public class AsyncTaskActivity extends AppCompatActivity {

    @InjectView(R.id.btn_AsyncTask)
    Button btnAsyncTask;
    @InjectView(R.id.tv_return)
    TextView tvReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        ButterKnife.inject(this);


    }


    @OnClick(R.id.btn_AsyncTask)
    public void onClick() {
        //调用异步任务，获取数据
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        //传url，get
//        String url = PathUrl.appUrl+"/LoginServlet?userName=管理员&userPwd=123";
        //post方式
        String url = PathUrl.appUrl+"/LoginServlet";
        //执行
        myAsyncTask.execute(url);
    }

    //泛型参数分别对应哪些方法？？如下：
    //Params(doInBackground的输入参数), Progress(onProgressUpdate的输入参数), Result( doInBackground的返回类型，onPostExecute的输入参数)
    class MyAsyncTask extends AsyncTask<String,Integer,String>{

        /**
         * 在子线程中执行：做一些耗时的操作
         * @param params  ？？值谁传过来==》execute
         * @return 执行耗时操作返回的结果：网络访问，服务器返回的数据
         */
        @Override
        protected String doInBackground(String... params) {
            //publishProgress(123);  ??

            //需用户显式调用：onProgressUpdate
            //publishProgress()；
            //访问网络
            //第一个参数：url
            String urlStr = params[0];   //默认传来的第一个参数是url
            HttpURLConnection httpURLConnection = null;
            InputStream is = null;
            ByteArrayOutputStream baos = null;
            OutputStream os = null;
            try {
                URL url = new URL(urlStr);
                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("GET");//设置请求方式为get
                httpURLConnection.setRequestMethod("POST");//设置请求方式为get
                httpURLConnection.setConnectTimeout(5000);//设置链接超时时间
                httpURLConnection.setReadTimeout(5000);//设置读取数据超时时间
                //设置post请求的参数
                os = httpURLConnection.getOutputStream();
                //写入请求体里
                os.write("userName=管理员&userPwd=123".getBytes());
                //判断服务器是否响应成功
                if(httpURLConnection.getResponseCode()==200){
                    //获得服务器返回的数据
                    is = httpURLConnection.getInputStream();
                    //转换成字符串
                    baos = new ByteArrayOutputStream();
                    int i;
                    while ((i=is.read())!=-1){
                        baos.write(i);
                    }

                    //返回服务器获取的结果
                    return baos.toString();

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {  // 关闭流和连接
                    if(baos!=null){
                        baos.close();
                    }
                    if(is!=null){
                        is.close();
                    }
                    httpURLConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return "读取失败！";
        }

        /**
         * doInBackground执行前：做一些初始化
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("MyAsyncTask", "onPreExecute: ");
        }

        /**
         *  doInBackground执行过程中，通过一定的方法来调用
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("MyAsyncTask", "onProgressUpdate: ");
        }

        /**
         * doInBackground执行之后： UI操作
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("MyAsyncTask", "onPostExecute: ");
            tvReturn.setText(s);
        }

        @Override
        protected void onCancelled(String s) {  //撤销操作？？
            Log.i("MyAsyncTask", "onCancelled: ");
            super.onCancelled(s);
        }

    }



}
