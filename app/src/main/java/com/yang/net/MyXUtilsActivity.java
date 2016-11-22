package com.yang.net;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.yang.day01.R;
import com.yang.util.PathUrl;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MyXUtilsActivity extends AppCompatActivity {


    @InjectView(R.id.btn_test)
    Button btn_test;
    @InjectView(R.id.btn_test2)
    Button btn_test2;
    @InjectView(R.id.btn_test_image)
    Button btn_test_image;
    @InjectView(R.id.iv)
    ImageView iv;
    @InjectView(R.id.btn_sendImageToServer)
    Button btn_sendImageToServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_xutils);
        ButterKnife.inject(this);


    }

    @OnClick({R.id.btn_test, R.id.btn_test2, R.id.btn_test_image,R.id.btn_sendImageToServer})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_test:   //get方式（头提交：用的是addQueryStringParameter()方法）
                RequestParams requestParams = new RequestParams(PathUrl.appUrl+"/LoginServlet");
                //requestParams.setSslSocketFactory(...);//设置ssl
                requestParams.addQueryStringParameter("userName", "管理员");
                requestParams.addQueryStringParameter("userPwd", "123");
                Callback.Cancelable cancelable=x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        //服务端响应成功则会回调该方法
                        Toast.makeText(MyXUtilsActivity.this, result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        //服务器响应失败则会回掉该方法
                        Toast.makeText(MyXUtilsActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        //执行cancel()方法则会回掉该方法
                        //x.http().... 方法本身就返回的是Callback.Cancelable对象
                        //cancelable.cancel();则会调用onCancelled-onFinished
                        Toast.makeText(MyXUtilsActivity.this, "cancelled-get", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinished() {
                        //不管是否相应成功，总会回掉该方法
                        Toast.makeText(MyXUtilsActivity.this, "finish-get", Toast.LENGTH_SHORT).show();
                        Log.i("MyXUtilsActivity", "onFinished: 完成");
                    }
                });
                cancelable.cancel();  //执行cancel()方法则不会执行onSuccess(),执行onCancelled方法
                break;
            case R.id.btn_test2: //post方式：（头提交：用的是addBodyParameter()方法）
                String url = PathUrl.appUrl+"/LoginServlet";
                String userName = "管理员";
                String userPwd = "123";
                RequestParams requestParams2 = new RequestParams(url);
                requestParams2.addBodyParameter("userName",userName);
                requestParams2.addBodyParameter("userPwd",userPwd);

                x.http().post(requestParams2, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MyXUtilsActivity.this, "登录成功-post", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(MyXUtilsActivity.this, "登录失败-post", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.i("MyXUtilsActivity", "onCancelled: ");
                    }

                    @Override
                    public void onFinished() {
                        Log.i("MyXUtilsActivity", "onFinished: ");
                    }
                });

                break;
            case R.id.btn_test_image: //从服务器读取图片（内部也会有三级缓存机制，具体看源代码）
                String url2 = PathUrl.serverUrl+"/image/dog.jpg";
                //设置图片样式
                ImageOptions imageOptions = new ImageOptions.Builder().setCircular(true)
                        .setFailureDrawableId(R.mipmap.ic_launcher)//失败图片
                        .setLoadingDrawableId(R.mipmap.head) //加载时的图片
                        .setCrop(true)   //是否裁剪？
                        .setAutoRotate(true).build();
                x.image().bind(iv,url2,imageOptions);
                break;
            case R.id.btn_sendImageToServer:              //向服务上传图片数据
                File sdFile = Environment.getExternalStorageDirectory();
                File f = new File(sdFile+"/xiaoyuanershou/image/tower.png");
                String url3 = PathUrl.appUrl+"/ImageUploadServlet";
                RequestParams requestParams3 = new RequestParams(url3);
                requestParams3.setMultipart(true);  //指定上传文件格式
                requestParams3.addBodyParameter("file",f);
                x.http().post(requestParams3, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("MyXUtilsActivity", "MyXUtilsActivity: onSuccess");
                        Toast.makeText(MyXUtilsActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.i("MyXUtilsActivity", "MyXUtilsActivity: onError");
                        Toast.makeText(MyXUtilsActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        Log.i("MyXUtilsActivity", "MyXUtilsActivity: onCancelled");
                        Toast.makeText(MyXUtilsActivity.this, "你取消了上传", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinished() {
                        Log.i("MyXUtilsActivity", "MyXUtilsActivity: onFinished");

                    }
                });

        }
    }





}
