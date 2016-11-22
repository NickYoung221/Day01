package com.yang.cache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.yang.day01.R;
import com.yang.util.PathUrl;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 根据给定的url和ImageView，将网络图片显示在ImageView上面（三级缓存机制）
 */
public class LoadImageActivity extends AppCompatActivity {

    @InjectView(R.id.btn_getImageFromNet)
    Button btnGetImageFromNet;
    @InjectView(R.id.iv)
    ImageView iv;

    MyImageLoader myImageLoader;
    String url = PathUrl.serverUrl+"/image/dog.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);
        ButterKnife.inject(this);

        myImageLoader = new MyImageLoader(this);
    }


    @OnClick(R.id.btn_getImageFromNet)
    public void onClick() {
        myImageLoader.showImageByUrl(url,iv);
    }


}
