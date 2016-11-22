package com.yang.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.yang.cache.util.DiskLruCacheUtil;
import com.yang.cache.util.LruCacheUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/** 根据给定的url和ImageView，将网络图片显示在ImageView上面（三级缓存机制）
 * Created by yang on 2016/9/26 0026.
 */
public class MyImageLoader {
    Context context;
    LruCacheUtil lruCacheUtil;
    DiskLruCacheUtil diskLruCacheUtil;

    public MyImageLoader(Context context){
        this.context = context;
        lruCacheUtil = new LruCacheUtil();
        diskLruCacheUtil = new DiskLruCacheUtil(context);
    }

    //通过url路径把网络图片展现在ImageView上面，并进行缓存处理
    public void showImageByUrl(String url,ImageView iv){
        //从内存中取,若取到，显示且return，没取到再去硬盘缓存中取，取到，显示且return，没取到再去网络获取
        if(getBitmapFromLruCache(url,iv)){
            Log.i("MyImageLoader", "MyImageLoader: showImageByUrl:内存缓存中取到");
            return;
        }else if(getImageFromDisk(url,iv)){
            Log.i("MyImageLoader", "MyImageLoader: showImageByUrl:硬盘缓存中取到");
            return;
        }else{
            getImageFromInternet(url,iv);
            Log.i("MyImageLoader", "MyImageLoader: showImageByUrl:正在从网络获取...");
        }

        //没有取到，1)AsyncTask获取图片，显示在Iv 2)加到lrucache
    }


    //从LruCache中获取图片，返回值为是否取到
    public boolean getBitmapFromLruCache(String url,ImageView iv){
        //先从LruCache中取到数据，若取到，显示在iv上
        Bitmap bitmap = lruCacheUtil.readFromLruCache(url);

        if(bitmap!=null){
            //取到图片，直接显示
            iv.setImageBitmap(bitmap);
            Log.i("MyImageLoader", "MyImageLoader: getBitmapFromLruCache:内存缓存取：取到");
            return true;
        }else{
            Log.i("MyImageLoader", "MyImageLoader: getBitmapFromLruCache:内存缓存取：没有取到");
            return false;
        }
    }


    //从硬盘缓存读取数据，判断是否读取到数据，取到，显示在iv上，且return true
    /**
     *
     * @param url
     * @param iv
     * @return 如果读取到数据，1）直接显示 2）加入到内存缓存；
     *   没有读取到，返回false
     */
    public boolean getImageFromDisk(String url,ImageView iv){
        //读取DiskLruCache数据
        Bitmap bitmap = diskLruCacheUtil.readFromDiskLruCache(url);

        if(bitmap!=null){
            //1、显示
            iv.setImageBitmap(bitmap);
            //2、加到内存缓存
            lruCacheUtil.writeToLruCache(url,bitmap);
            Log.i("MyImageLoader", "MyImageLoader: getImageFromDiskLrucache:硬盘中取到，加到内存缓存中");
            return true;
        }else{
            Log.i("MyImageLoader", "MyImageLoader: getImageFromDiskLrucache:硬盘缓存取：硬盘中没有取到");
            return false;
        }

    }


    //从网络中获取图片，获取到，加入到内存缓存中
    public void getImageFromInternet(String url,ImageView iv){
        MyAsyncTask myAsyncTask = new MyAsyncTask(iv); //获取MyAsyncTask对象
        myAsyncTask.execute(url);  //参数传给doInBackground
    }


    //定义异步任务
    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{
        String urlStr;
        ImageView iv;
        public MyAsyncTask(ImageView iv){
            this.iv = iv;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                //获得第一个参数：url
                urlStr = params[0];
                URL url = new URL(urlStr);
                //访问网络，获取bitmap对象
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                //响应成功
                if(httpURLConnection.getResponseCode()==200){
                    InputStream is = httpURLConnection.getInputStream();//获取网络返回数据的输入流
                    Bitmap bitmap = BitmapFactory.decodeStream(is);//将输入流传换为Bitmap对象
                    return bitmap;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //响应不成功，则返回null
            Log.i("MyImageLoader", "MyAsyncTask: doInBackground:网络获取，网络获取不到！");
            return null;
        }

        //执行UI操作，以及加到内存缓存，硬盘缓存等操作
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap==null){//网络获取不到，后面就不只执行
                return;
            }
            //1、显示在iv上
            iv.setImageBitmap(bitmap);
            //2、加到内存缓存
            lruCacheUtil.writeToLruCache(urlStr,bitmap);
            Log.i("MyImageLoader", "MyAsyncTask: onPostExecute:网络获取到，加到内存缓存");
            //2、加到硬盘缓存
            diskLruCacheUtil.writeToDiskLruCache(urlStr,bitmap);
            Log.i("MyImageLoader", "MyAsyncTask: onPostExecute:网络获取到，加到硬盘缓存");
        }

    }





}
