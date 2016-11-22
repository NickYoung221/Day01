package com.yang.cache.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.yang.cache.libcore.io.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** 硬盘缓存
 * Created by yang on 2016/9/26 0026.
 */
public class DiskLruCacheUtil {

    DiskLruCache diskLruCache;

    public DiskLruCacheUtil(Context context){
        //1、创建DiskLruCache对象
        /**
         * 参数一：缓存路径：缓存根路径+image
         * 参数二：版本号
         * 参数三：1个key对应几个value
         * 参数四：硬盘缓存的大小：50M
         */
        try {
            diskLruCache = DiskLruCache.open(getDiskCacheDir(context,"imageCache"),getAppVersion(context),1,50*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //2、向DiskLruCache中写数据
    public void writeToDiskLruCache(String url, Bitmap bitmap){
        //key:url做MD5编码（不进行MD5编码的话会报错）
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(MD5Util.hashKeyForDisk(url));

            if(editor!=null){
                OutputStream os = editor.newOutputStream(0);//获取key对应的第一个value

                //bitmap数据，写入到outputstream;  返回，是否写入成功
                //compress()方法：参数一：转化的图片格式，参数二：图片质量（1~100）参数三：输出流
                boolean success = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);

                if(success){
                    editor.commit();
                }else {
                    editor.abort(); //取消
                }
                diskLruCache.flush();//刷新，否则无法写入流
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //从disklrucache中取数据
    public Bitmap readFromDiskLruCache(String url){
        //key:做了MD5编码
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(MD5Util.hashKeyForDisk(url));

            if(snapshot!=null){
                InputStream is = snapshot.getInputStream(0);//获取key对应的第一个value的输入流
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //获取硬盘目录
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    //获取应用版本号：
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
